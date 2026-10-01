package vbsp.ims.sso;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;
import javax.servlet.http.HttpSession;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.Gson;
import java.time.Instant;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.Arrays;
import vbsp.ims.sso.SsoUserDto.AppDto;
import vbsp.ims.restapi.DuLieuNTService;

public class BeforeLoginAction extends ActionSupport {

    private static final long serialVersionUID = 1L;

    private DuLieuNTService _serverAPI = new DuLieuNTService();
    private MenuBuildingService menuBuildingService = new MenuBuildingService();
    private List<SessionDto> lstToken;
    private SsoServiceToken ssoService = new SsoServiceToken();
    private SsoServiceUserInfo userInfoService = new SsoServiceUserInfo();
    private SsoMenuAction menuActionService = new SsoMenuAction();

    private List<MenuDataDto> lstMenu;
    private String code;
    private String state;
    private List<BranchCodeByPosCd> lstBranchCode;
    private String selectedRole;
    private String selectedUserLevel;
    private List<String> userLevels;

    @Override
    public String execute() throws Exception {
        HttpSession session = ServletActionContext.getRequest().getSession();
        System.err.println("SSO clicked - Received code: " + code);
        if (code == null || code.trim().isEmpty()) {
            addActionError("Không tìm thấy mã xác thực Authorization Code!");
            return ERROR;
        }
        System.out.println("code: " + code);

        // 1. Đổi code lấy Token
        String tokenJsonResponse = ssoService.exchangeCodeForToken(code);
        System.out.println("tokenJsonResponse từ SSO: " + tokenJsonResponse);
        if (tokenJsonResponse == null || tokenJsonResponse.isEmpty()) {
            addActionError("Đổi mã Token thất bại từ hệ thống SSO!");
            return ERROR;
        }

        try {
            session.setAttribute("SSO_TOKEN_RESPONSE", tokenJsonResponse);

            JsonParser parser = new JsonParser();
            JsonObject tokenJson = parser.parse(tokenJsonResponse).getAsJsonObject();
            String accessToken = tokenJson.has("access_token") ? tokenJson.get("access_token").getAsString() : "";
            System.out.println("token: " + accessToken);
            if (accessToken.isEmpty()) {
                addActionError("Đổi mã Token thất bại từ hệ thống SSO!");
                return ERROR;
            }

            // 2. Lấy thông tin User Info
            String userInfoJsonResponse = userInfoService.getUserInfo(accessToken);
            if (userInfoJsonResponse == null || userInfoJsonResponse.isEmpty()) {
                return ERROR;
            }
            session.setAttribute("SSO_USER_INFO", userInfoJsonResponse);
            // check ss
//            _serverAPI.getSessionDatas(userInfoJsonResponse, code);
            Gson gson = new Gson();
            SsoUserDto userDto = gson.fromJson(userInfoJsonResponse, SsoUserDto.class);

            // 3. Kiểm tra quyền ứng dụng "TTBC"
            if (!hasValidTTBCApp(userDto)) {
                addActionError("Tài khoản của bạn không có quyền truy cập vào Hệ thống Thông tin Báo cáo (TTBC)!");
                return ERROR;
            }

            // Lưu thông tin user vào session
            saveUserSessionAttributes(session, userDto);

            String refreshToken = tokenJson.has("refresh_token") ? tokenJson.get("refresh_token").getAsString() : "";

            ArrayList<SessionUpdateDto> lstUpdateDate = new ArrayList<>();
            {
                SessionUpdateDto dto = new SessionUpdateDto();
                dto.setUserCode(code);
                dto.setAccessToken(accessToken);
                dto.setRefreshToken(refreshToken);
                String currentTime = Instant.now().toString();
                dto.setExpiresAt(currentTime);
                dto.setLoginTime(currentTime);
                dto.setStatus(1);
                lstUpdateDate.add(dto);
            }
            _serverAPI.callSessionUpdateApi(code, lstUpdateDate);

            // Xử lý danh sách user_level
            userLevels = extractUserLevels(userDto);
            session.setAttribute("USER_LEVELS", userLevels);

            // 4. Lấy Menu / Roles từ SSO Menu API
            String menuJsonResponse = menuActionService.getUserInfo(accessToken);
            if (menuJsonResponse == null || menuJsonResponse.isEmpty()) {
                return ERROR;
            }
            session.setAttribute("SSO_MENU_RESPONSE", menuJsonResponse);

            SsoMenuResponseDto menuRes = gson.fromJson(menuJsonResponse, SsoMenuResponseDto.class);
            List<String> roleNames = extractRoleNames(menuRes);
            session.setAttribute("USER_ROLES", roleNames);

            // --- ĐIỀU KIỆN GỘP CHUNG MÀN HÌNH CHỌN ---
            boolean needSelectUserLevel = (userLevels != null && userLevels.size() > 1);
            boolean needSelectRole = (roleNames != null && roleNames.size() > 1);

            if (needSelectUserLevel || needSelectRole) {
                // Nếu chưa có giá trị được chọn trước đó, lấy mặc định phần tử đầu tiên cho an toàn
                if (selectedUserLevel == null || selectedUserLevel.isEmpty()) {
                    selectedUserLevel = userLevels.get(0);
                }
                if (selectedRole == null || selectedRole.isEmpty()) {
                    selectedRole = roleNames.get(0);
                }
                return "select_combined";
            }

            // Nếu cả 2 đều <= 1 lựa chọn, tự động gán và đi tiếp
            selectedUserLevel = (userLevels != null && !userLevels.isEmpty()) ? userLevels.get(0) : "1";
            selectedRole = (roleNames != null && !roleNames.isEmpty()) ? roleNames.get(0) : "";

            session.setAttribute("SELECTED_USER_LEVEL", selectedUserLevel);
            session.setAttribute("SELECTED_ROLE", selectedRole);

            return processMenuBuilding(menuRes, selectedRole);

        } catch (Exception e) {
            e.printStackTrace();
            addActionError("Lỗi hệ thống trong quá trình đăng nhập!");
            return ERROR;
        }
    }

    // lấy ss cho role + level
    public String selectCombinedAndBuildMenu() {
        HttpSession session = ServletActionContext.getRequest().getSession();
        String menuJsonResponse = (String) session.getAttribute("SSO_MENU_RESPONSE");

        if (menuJsonResponse == null || menuJsonResponse.isEmpty()) {
            addActionError("Phiên làm việc đã hết hạn, vui lòng đăng nhập lại!");
            return ERROR;
        }

        try {
            Gson gson = new Gson();
            SsoMenuResponseDto menuRes = gson.fromJson(menuJsonResponse, SsoMenuResponseDto.class);
            session.setAttribute("SELECTED_USER_LEVEL", selectedUserLevel);
            session.setAttribute("reportGrade", selectedUserLevel);
            session.setAttribute("SELECTED_ROLE", selectedRole);
            session.setAttribute("roleNames", selectedRole);
            System.out.println("selectedUserLevel: " + selectedUserLevel);
            System.out.println("selectedRole: " + selectedRole);
            // 3. Tiến hành dựng menu và trả về trang chính
            return processMenuBuilding(menuRes, selectedRole);

        } catch (Exception e) {
            e.printStackTrace();
            addActionError("Lỗi hệ thống khi thiết lập thông tin làm việc!");
            return ERROR;
        }
    }

    //Action lấy role
    public String selectRoleAndBuildMenu() {
        HttpSession session = ServletActionContext.getRequest().getSession();
        String menuJsonResponse = (String) session.getAttribute("SSO_MENU_RESPONSE");

        if (menuJsonResponse == null || menuJsonResponse.isEmpty()) {
            addActionError("Phiên làm việc đã hết hạn, vui lòng đăng nhập lại!");
            return ERROR;
        }

        try {
            Gson gson = new Gson();
            SsoMenuResponseDto menuRes = gson.fromJson(menuJsonResponse, SsoMenuResponseDto.class);

            if (selectedRole == null || selectedRole.isEmpty()) {
                selectedRole = (String) session.getAttribute("SELECTED_ROLE");
            } else {
                session.setAttribute("SELECTED_ROLE", selectedRole);
            }

            return processMenuBuilding(menuRes, selectedRole);

        } catch (Exception e) {
            e.printStackTrace();
            addActionError("Lỗi hệ thống khi chọn vai trò!");
            return ERROR;
        }
    }

    private List<String> extractUserLevels(SsoUserDto userDto) {
        List<String> levels = new ArrayList<>();
        if (userDto.getUser_level() != null) {
            levels.addAll(userDto.getUser_level());
        }
        if (levels.isEmpty()) {
            levels.add("1");
        }
        return levels;
    }

    private boolean hasValidTTBCApp(SsoUserDto userDto) {
        if (userDto.getApplication() != null) {
            for (AppDto app : userDto.getApplication()) {
                if ("TTBC".equals(app.getClient_id())) {
                    return true;
                }
            }
        }
        return false;
    }

    private List<String> extractRoleNames(SsoMenuResponseDto menuRes) {
        List<String> roleNames = new ArrayList<>();
        if (menuRes.getRoles() != null) {
            for (SsoMenuResponseDto.RoleItemDto r : menuRes.getRoles()) {
                roleNames.add(r.getRole());
            }
        }
        return roleNames;
    }

    private String processMenuBuilding(SsoMenuResponseDto menuRes, String targetRole) {
        HttpSession session = ServletActionContext.getRequest().getSession();

        SsoMenuResponseDto.RoleItemDto chosenRoleObj = null;
        if (menuRes.getRoles() != null) {
            for (SsoMenuResponseDto.RoleItemDto r : menuRes.getRoles()) {
                if (r.getRole().equals(targetRole)) {
                    chosenRoleObj = r;
                    break;
                }
            }
        }

        if (chosenRoleObj == null) {
            addActionError("Vai trò (Role) được chọn không tồn tại trong hệ thống!");
            return ERROR;
        }

        lstMenu = _serverAPI.getMenuData();
        if (lstMenu == null || lstMenu.isEmpty()) {
            addActionError("Cấu hình menu từ Database không tồn tại!");
            return ERROR;
        }

        List<String> allowedMenus = Arrays.asList("TTBC_1", "TTBC_2", "TTBC_3", "TTBC_4", "TTBC_5", "TTBC_94", "TTBC_118");
        List<String> userAllowedResourceKeys = new ArrayList<>();
        List<String> actionList = new ArrayList<>();
        List<String> menuList = new ArrayList<>();

        menuBuildingService.extractPermissionsForSingleRole(chosenRoleObj, userAllowedResourceKeys, actionList);
        Map<String, MenuDataDto> dbMenuMap = menuBuildingService.createDbMenuMap(lstMenu);

        Map<MenuDataDto, Map<MenuDataDto, List<MenuDataDto>>> menuTree3Levels
                = menuBuildingService.buildMenuTree3Levels(allowedMenus, userAllowedResourceKeys, chosenRoleObj, dbMenuMap, lstMenu, menuList);

        session.setAttribute("USER_MENU_TREE_3LEVELS", menuTree3Levels);
        session.setAttribute("USER_ACTIONS", actionList);
        session.setAttribute("USER_MENUS", menuList);
        session.setAttribute("CURRENT_ACTIVE_ROLE", targetRole);

        return SUCCESS;
    }

    private void saveUserSessionAttributes(HttpSession session, SsoUserDto userDto) {
        session.setAttribute("USER_SESSION", userDto);
        session.setAttribute("USERNAME", userDto.getPreferred_username());
        session.setAttribute("username", userDto.getPreferred_username());
        session.setAttribute("SUB", userDto.getSub());
        session.setAttribute("PHONE_NUMBER", userDto.getPhone_number());
        session.setAttribute("FAMILY_NAME", userDto.getFamily_name());
        session.setAttribute("ID_NO", userDto.getId_no());
        session.setAttribute("ISSUE_DATE", userDto.getIssue_date());
        session.setAttribute("ISSUE_PLACE", userDto.getIssue_place());
        session.setAttribute("POS_CODE", userDto.getPos_code());
        session.setAttribute("POS_NAME", userDto.getPos_name());
        session.setAttribute("USER_GROUP", userDto.getUser_group());
        session.setAttribute("USER_GRADE", userDto.getUser_grade());
        session.setAttribute("USER_TYPE", userDto.getUser_type());
        session.setAttribute("ROLE", userDto.getRole());
        session.setAttribute("USER_LEVEL", userDto.getUser_level());
        session.setAttribute("NAME", userDto.getName());
        session.setAttribute("EMAIL", userDto.getEmail());
        session.setAttribute("EMPLOYEE_ID", userDto.getEmployee_id());
        session.setAttribute("ORGANIZATION_ID", userDto.getOrganization_id());
        session.setAttribute("ORGANIZATION_NAME", userDto.getOrganization_name());
        session.setAttribute("DEPARTMENT_ID", userDto.getDepartment_id());
        session.setAttribute("POSITION_ID", userDto.getPosition_id());
        session.setAttribute("POSITION_NAME", userDto.getPosition_name());
        session.setAttribute("APPLICATION", userDto.getApplication());

        lstBranchCode = _serverAPI.getMainPos(userDto.getPos_code());
        String macn = "", tencn = "";
        if (lstBranchCode != null && !lstBranchCode.isEmpty()) {
            macn = lstBranchCode.get(0).getMacn();
            tencn = lstBranchCode.get(0).getTen();
        }
        session.setAttribute("MA_CN", macn);
        session.setAttribute("TEN_CN", tencn);
    }

    //<editor-fold defaultstate="collapsed" desc="get set">
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public List<MenuDataDto> getLstMenu() {
        return lstMenu;
    }

    public void setLstMenu(List<MenuDataDto> lstMenu) {
        this.lstMenu = lstMenu;
    }

    public List<BranchCodeByPosCd> getLstBranchCode() {
        return lstBranchCode;
    }

    public void setLstBranchCode(List<BranchCodeByPosCd> lstBranchCode) {
        this.lstBranchCode = lstBranchCode;
    }

    public String getSelectedRole() {
        return selectedRole;
    }

    public void setSelectedRole(String selectedRole) {
        this.selectedRole = selectedRole;
    }

    public String getSelectedUserLevel() {
        return selectedUserLevel;
    }

    public void setSelectedUserLevel(String selectedUserLevel) {
        this.selectedUserLevel = selectedUserLevel;
    }

    public List<String> getUserLevels() {
        return userLevels;
    }

    public void setUserLevels(List<String> userLevels) {
        this.userLevels = userLevels;
    }
    //</editor-fold>
}
