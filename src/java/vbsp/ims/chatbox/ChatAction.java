package vbsp.ims.chatbox;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import org.apache.struts2.ServletActionContext;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import vbsp.ims.restapi.DuLieuNTService;
import vbsp.ims.restapi.ListOfValue;

public class ChatAction extends ActionSupport {
//<editor-fold defaultstate="collapsed" desc="Khai bao cac bien">

    private HttpServletRequest request;
    private String question;
    private String type;
    protected String UserName;
    protected String Message;
    private List<ListOfValue> data;
    private DuLieuNTService _serverAPI = new DuLieuNTService();
    private String response;
    private InputStream inputStream;

    public HttpServletRequest getRequest() {
        return request;
    }

    public void setRequest(HttpServletRequest request) {
        this.request = request;
    }

    public InputStream getInputStream() {
        return inputStream;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public DuLieuNTService getServerAPI() {
        return _serverAPI;
    }

    public void setServerAPI(DuLieuNTService _serverAPI) {
        this._serverAPI = _serverAPI;
    }

    public List<ListOfValue> getData() {
        return data;
    }

    public void setData(List<ListOfValue> data) {
        this.data = data;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String UserName) {
        this.UserName = UserName;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String Message) {
        this.Message = Message;
    }
//</editor-fold>

    public String openChat() {
        return SUCCESS;
    }

    public String checkMachine() {

        try {

            HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get("com.opensymphony.xwork2.dispatcher.HttpServletRequest");

            String ip = request.getRemoteAddr();

            if ("0:0:0:0:0:0:0:1".equals(ip) || "::1".equals(ip)) {
                ip = "127.0.0.1";
            }
            response = "IP : " + ip;
        } catch (Exception e) {
            e.printStackTrace();
            response = "Không thể xác định IP máy: " + e.getMessage();
        }

        inputStream = new ByteArrayInputStream(response.getBytes(StandardCharsets.UTF_8));

        return SUCCESS;
    }

    public String askAI() {
        try {
            Map<String, Object> session = ActionContext.getContext().getSession();

            if (session == null || session.get("username") == null) {
                sendResponse("Phiên làm việc đã hết hạn. Vui lòng đăng nhập lại.");
                return NONE;
            }

            UserName = session.get("username").toString();
            data = _serverAPI.getListOfValue("305", "");

            if (data == null || data.isEmpty()) {
                sendResponse("Không có dữ liệu tại máy chủ TTCNTT.");
                return NONE;
            }

            String aiResponse = null;

            for (ListOfValue item305 : data) {
                if (!"A".equals(item305.getStatus())) {
                    continue;
                }

                String code = item305.getCode();
                String value = item305.getValue();
                String description = item305.getDescription();
                String url = null;
                String apiKey = null;

                List<ListOfValue> data_2 = _serverAPI.getListOfValue("304", value);

                if (data_2 != null && !data_2.isEmpty()) {
                    for (ListOfValue item304 : data_2) {
                        if (!"A".equals(item304.getStatus())) {
                            continue;
                        }

                        url = item304.getValue();
                        apiKey = item304.getDescription();
                        break;
                    }
                }

                try {
                    aiResponse = AIService.ask(question, type, UserName, code, value, description, url, apiKey);

                    if (aiResponse != null && !aiResponse.trim().isEmpty()) {
//                        System.out.println("AI " + code + " trả lời thành công.");
                        break;
                    }

                } catch (Exception e) {
//                    System.out.println("AI " + code + " lỗi, chuyển AI tiếp theo.");
                    e.printStackTrace();
                }
            }

            sendResponse(aiResponse != null && !aiResponse.trim().isEmpty() ? aiResponse.trim() : "Tôi chưa được hướng dẫn về vấn đề này.");

        } catch (Exception e) {
            e.printStackTrace();
            sendResponse("Có lỗi hệ thống xảy ra.");
        }

        return NONE;
    }

    private void sendResponse(String text) {
        try {
            ServletActionContext.getResponse().setContentType("text/plain;charset=UTF-8");
            ServletActionContext.getResponse().setCharacterEncoding("UTF-8");

            PrintWriter out = ServletActionContext.getResponse().getWriter();
            out.print(text);
            out.flush();
            out.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
