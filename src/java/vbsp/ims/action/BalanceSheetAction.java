/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vbsp.ims.action;

import com.jgeppert.struts2.jquery.tree.result.TreeNode;
import static com.opensymphony.xwork2.Action.SUCCESS;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.interceptor.ServletRequestAware;
import vbsp.ims.dao.BuildPosTreeDao;
import vbsp.ims.dao.IMSRptDao;
import vbsp.ims.define.DefineFun;
import vbsp.ims.model.Account;
import vbsp.ims.model.BalanceSheetExporter;
import vbsp.ims.model.BalanceSheetManager;
import vbsp.ims.report.fast.ListValue;

//@Action(value = "/loadTree", results = { @Result(location = "treedemo.jsp", name = "success") })
public class BalanceSheetAction extends ActionSupport
        implements ServletRequestAware, ModelDriven {

    private static final long serialVersionUID = 6518221459701336965L;

    private TreeNode nodes;
    HttpServletRequest request;
    private String selectedPos;
    private List<ListValue> periodList;
    private String selectedPeriod;
    private List<Account> accountList;
    private String reportDate;
    private String userName;
    private int reportGrade;
    private String editParam;
    private Account account;
    private String balanceSheetType;
    private TreeNode searchNodes;
    private String consolidateFlag;
    private String searchAccount;
    private String searchType;
    private String printType;
    private String printSize;
    private String filereport;
    private String fileNamelocal;
    private List<String> editParamList;

    private BigDecimal editOpenDebit;
    private BigDecimal editOpenCredit;
    private BigDecimal editTurnDebit;
    private BigDecimal editTurnCredit;
    private BigDecimal editCloseDebit;
    private BigDecimal editCloseCredit;
    private String message;

    public BalanceSheetAction() {
    }

    public String buildTreeView()
            throws Exception {
        if (request.getSession().getAttribute("startTreeGLKHTDRecursive") != null) {
            return SUCCESS;
        } else {
            reportGrade = Integer.parseInt(request.getSession().getAttribute("reportGrade").toString());
            userName = request.getSession().getAttribute("username").toString();
            BuildPosTreeDao buildPosTreeDao = new BuildPosTreeDao(reportGrade, userName);
            buildPosTreeDao.build();
            this.nodes = buildPosTreeDao.getNodes();
            request.getSession().setAttribute("startTreeGLKHTDRecursive", "false");
            return SUCCESS;
        }
    }

    /* Hàm sinh chuỗi kỳ báo cáo*/
    public String generatePeriodCombo() {
        this.periodList = IMSRptDao.getPeriodList();
        return "populate";
    }

    /* Hàm view cân đối */
    public String viewBalanceSheet() {
        if (this.searchNodes == null) {            
            try {
                reportGrade = Integer.parseInt(
                        request.getSession().getAttribute("reportGrade").toString()
                );
                
                userName = request.getSession().getAttribute("username").toString();                
                
                System.err.print("searchNodes == null"+userName+reportGrade);                
                
                BuildPosTreeDao buildPosTreeDao = new BuildPosTreeDao(reportGrade, userName);
                buildPosTreeDao.build();
                
                this.searchNodes = buildPosTreeDao.getNodes();
                
            } catch (Exception ex) {
                System.err.println("viewBalanceSheet[1]-"+ex.getMessage());
            }
        }
        String listOfPos = getListOfPos();
        System.err.print("viewBalanceSheet() " + reportDate + request.getParameter("buttonId")
                + balanceSheetType + selectedPos + listOfPos + selectedPeriod);
//        BalanceSheetDao balanceSheetDao
//                = new BalanceSheetDao(DefineFun.convert2OracleDateFormat(reportDate),
//                        listOfPos, this.consolidateFlag, this.balanceSheetType,this.selectedPeriod);
//        balanceSheetDao.generate();
//        accountList = balanceSheetDao.getAccountList();
        accountList = BalanceSheetManager.list(reportDate, listOfPos,
                consolidateFlag, balanceSheetType, selectedPeriod);
        System.err.println("viewBalanceSheet() is finished generated data");
        return "success";
    }

    public String printBalanceSheet() {
        if (this.searchNodes == null) {
            System.err.print("searchNodes is null");
            try {
                reportGrade = Integer.parseInt(request.getSession().getAttribute("reportGrade").toString());
                userName = request.getSession().getAttribute("username").toString();
                BuildPosTreeDao buildPosTreeDao = new BuildPosTreeDao(reportGrade, userName);
                buildPosTreeDao.build();
                this.searchNodes = buildPosTreeDao.getNodes();
            } catch (SQLException ex) {
            }
        }
        System.err.println("printSize: " + printSize);
        if (this.printType.trim().isEmpty()) {
            System.err.print("printBalanceSheets() --> Stop");
        } else {
            String listOfPos = getListOfPos();
            String posOfUser = IMSRptDao.getPosOfUser(userName);
            String lcRootDirPath = !request.getRealPath("/").endsWith("/")?request.getRealPath("/")+"/":request.getRealPath("/");
            //System.err.println("Da vao ham tao printBalanceSheets()" + this.printType);
            /* Trường hợp chưa xem nhưng in*/
            if (printType.equals("03") && !BalanceSheetManager.hasViewed) {
                accountList = BalanceSheetManager.list(reportDate, listOfPos,
                        consolidateFlag, balanceSheetType, selectedPeriod);
            }
            BalanceSheetExporter balanceSheetExporter;
            if (printType.equals("03")) {
                List<Account> fullAccounts = BalanceSheetManager.listFullAccount(reportDate, listOfPos, 
                        consolidateFlag,balanceSheetType, selectedPeriod);
                balanceSheetExporter
                        = new BalanceSheetExporter(lcRootDirPath,
                                DefineFun.convert2OracleDateFormat(reportDate),
                                listOfPos, consolidateFlag, balanceSheetType,
                                selectedPeriod, printType,printSize, posOfUser,
                                fullAccounts);
            } else {
                balanceSheetExporter
                        = new BalanceSheetExporter(lcRootDirPath,
                                DefineFun.convert2OracleDateFormat(reportDate),
                                listOfPos, consolidateFlag, balanceSheetType,
                                selectedPeriod, printType,printSize, 
                                posOfUser, BalanceSheetManager.getAccountList());
            }
            ArrayList<String> filePathList = (ArrayList<String>) balanceSheetExporter.generate();
            filereport = filePathList.get(0);
            fileNamelocal = filePathList.get(1);
        }
        return "success";
    }

    public String export2Excel() {
        if (this.searchNodes == null) {
            System.err.print("searchNodes is null");
            try {
                reportGrade = Integer.parseInt(request.getSession().getAttribute("reportGrade").toString());
                userName = request.getSession().getAttribute("username").toString();
                BuildPosTreeDao buildPosTreeDao = new BuildPosTreeDao(reportGrade, userName);
                buildPosTreeDao.build();
                this.searchNodes = buildPosTreeDao.getNodes();
            } catch (SQLException ex) {
            }
        }
        // printType = "03" - Excel
        setPrintType("03");

        System.err.println("searchAccount " + searchAccount);

        String listOfPos = getListOfPos();
        String posOfUser = IMSRptDao.getPosOfUser(userName);
        String lcRootDirPath = !request.getRealPath("/").endsWith("/")?request.getRealPath("/")+"/":request.getRealPath("/");

        if (reportGrade == 3 && printType.equals("03")
                && (searchAccount == null || searchAccount.isEmpty())) {
            ResultSet rs = BalanceSheetManager.list_Rs(reportDate, listOfPos,
                    consolidateFlag, balanceSheetType, selectedPeriod);
            BalanceSheetExporter balanceSheetExporter;
            balanceSheetExporter
                    = new BalanceSheetExporter(lcRootDirPath,
                            DefineFun.convert2OracleDateFormat(reportDate),
                            listOfPos, consolidateFlag, balanceSheetType,
                            selectedPeriod, printType,printSize, posOfUser, rs);
            ArrayList<String> filePathList = (ArrayList<String>) balanceSheetExporter.generate_rs();
            filereport = filePathList.get(0);
            fileNamelocal = filePathList.get(1);
        } else {
            /* Trường hợp chưa xem nhưng in*/
            if (printType.equals("03") && (searchAccount == null || searchAccount.isEmpty())) {
                accountList = BalanceSheetManager.list(reportDate, listOfPos,
                        consolidateFlag, balanceSheetType, selectedPeriod);
            }
            BalanceSheetExporter balanceSheetExporter;
            if (searchAccount == null || searchAccount.isEmpty()) {
                balanceSheetExporter
                        = new BalanceSheetExporter(lcRootDirPath,
                                DefineFun.convert2OracleDateFormat(reportDate),
                                listOfPos, consolidateFlag, balanceSheetType,
                                selectedPeriod, printType,printSize,
                                posOfUser, BalanceSheetManager.getAccountList());
            } else {
                balanceSheetExporter
                        = new BalanceSheetExporter(lcRootDirPath,
                                DefineFun.convert2OracleDateFormat(reportDate),
                                listOfPos, consolidateFlag, balanceSheetType,
                                selectedPeriod, printType, printSize,
                                posOfUser, BalanceSheetManager.getSearchList());
            }
            ArrayList<String> filePathList = (ArrayList<String>) balanceSheetExporter.generate();
            filereport = filePathList.get(0);
            fileNamelocal = filePathList.get(1);
        }
        return "success";
    }

    public String searchBalanceSheet() {
        if (this.searchNodes == null) {
            System.err.print("searchNodes is null");
            try {
                reportGrade = Integer.parseInt(request.getSession().getAttribute("reportGrade").toString());
                userName = request.getSession().getAttribute("username").toString();
                BuildPosTreeDao buildPosTreeDao = new BuildPosTreeDao(reportGrade, userName);
                buildPosTreeDao.build();
                this.searchNodes = buildPosTreeDao.getNodes();
            } catch (SQLException ex) {
            }
        }
        String listOfPos = getListOfPos();
        System.err.print("searchBalanceSheet() " + searchAccount + "~" + balanceSheetType
                + "~" + listOfPos + "~" + consolidateFlag + "~" + searchType);
        this.accountList = BalanceSheetManager.filter(searchAccount, 
                balanceSheetType, 
                listOfPos,
                DefineFun.convert2OracleDateFormat(reportDate),
                selectedPeriod,
                consolidateFlag,
                searchType
                );
        this.account = BalanceSheetManager.getTotalAccount();
        System.err.print("searchBalanceSheet() " + account.getOpen_debit().toString()
                + account.getOpen_credit().toString());
        return "success";
    }

    public String checkAccountProperty() {
        if (balanceSheetType.equals("01") || balanceSheetType.equals("02")) {
            this.accountList = BalanceSheetManager.checkProperty();
            if (this.accountList.isEmpty()) {
                this.message = "(*)Không có tài khoản sai tính chất N/C";
                return "error";
            } else {
                return "success";
            }
        } else {
            this.message = "(*)Chức năng kiểm tra không thực hiện cho cân đối GL";
            return "error";
        }
    }

    /* Ham sua can doi */
    public String editAccountBalance() {
        editParamList = new ArrayList<>();
        editParamList = DefineFun.string2Array(editParam, "#", 1);
        this.editParam = editParamList.get(0) + "#" + editParamList.get(1);
        if (editParamList.get(1).length() == 4 && editParamList.get(1).startsWith("9")) {
            balanceSheetType = "02";
        } else if (editParamList.get(1).length() == 4) {
            balanceSheetType = "01";
        } else if (editParamList.get(1).startsWith("95")
                || editParamList.get(1).startsWith("96")) {
            balanceSheetType = "04";
        } else {
            balanceSheetType = "03";
        }
        if (balanceSheetType.equals("01") || balanceSheetType.equals("02")) {
            editOpenDebit = new BigDecimal(editParamList.get(2));
            editOpenCredit = new BigDecimal(editParamList.get(3));
            editTurnDebit = new BigDecimal(editParamList.get(4));
            editTurnCredit = new BigDecimal(editParamList.get(5));
            editCloseDebit = new BigDecimal(editParamList.get(6));
            editCloseCredit = new BigDecimal(editParamList.get(7));
            return SUCCESS;
        } else {
            return ERROR;
        }
    }

    //------------------------------------------------------------------------------------------
    public TreeNode getNodes() {
        return nodes;
    }

    public void setNodes(TreeNode nodes) {
        this.nodes = nodes;
    }

    @Override
    public void setServletRequest(HttpServletRequest request) {
        this.request = request;
    }

    public String getSelectedPos() {
        return selectedPos;
    }

    public void setSelectedPos(String selectedPos) {
        this.selectedPos = selectedPos;
    }

    public List<ListValue> getPeriodList() {
        return periodList;
    }

    public void setPeriodList(List<ListValue> periodList) {
        this.periodList = periodList;
    }

    public String getSelectedPeriod() {
        return selectedPeriod;
    }

    public void setSelectedPeriod(String selectedPeriod) {
        this.selectedPeriod = selectedPeriod;
    }

    public List<Account> getAccountList() {
        return accountList;
    }

    public void setAccountList(List<Account> accountList) {
        this.accountList = accountList;
    }

    public String getReportDate() {
        return reportDate;
    }

    public void setReportDate(String reportDate) {
        this.reportDate = reportDate;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public String getEditParam() {
        return editParam;
    }

    public void setEditParam(String editParam) {
        this.editParam = editParam;
    }

    public String getBalanceSheetType() {
        return balanceSheetType;
    }

    public void setBalanceSheetType(String balanceSheetType) {
        this.balanceSheetType = balanceSheetType;
    }

    public String getSearchAccount() {
        return searchAccount;
    }

    public void setSearchAccount(String searchAccount) {
        this.searchAccount = searchAccount;
    }

    public String getPrintType() {
        return printType;
    }

    public void setPrintType(String printType) {
        this.printType = printType;
    }

    public String getFilereport() {
        return filereport;
    }

    public String getSearchType() {
        return searchType;
    }

    public void setSearchType(String searchType) {
        this.searchType = searchType;
    }    
    
    public String getPrintSize() {
        return printSize;
    }

    public void setPrintSize(String printSize) {
        this.printSize = printSize;
    }        

    public void setFilereport(String filereport) {
        this.filereport = filereport;
    }

    public String getFileNamelocal() {
        return fileNamelocal;
    }

    public void setFileNamelocal(String fileNamelocal) {
        this.fileNamelocal = fileNamelocal;
    }

    public List<String> getEditParamList() {
        return editParamList;
    }

    public void setEditParamList(List<String> editParamList) {
        this.editParamList = editParamList;
    }

    public BigDecimal getEditOpenDebit() {
        return editOpenDebit;
    }

    public void setEditOpenDebit(BigDecimal editOpenDebit) {
        this.editOpenDebit = editOpenDebit;
    }

    public BigDecimal getEditOpenCredit() {
        return editOpenCredit;
    }

    public void setEditOpenCredit(BigDecimal editOpenCredit) {
        this.editOpenCredit = editOpenCredit;
    }

    public BigDecimal getEditTurnDebit() {
        return editTurnDebit;
    }

    public void setEditTurnDebit(BigDecimal editTurnDebit) {
        this.editTurnDebit = editTurnDebit;
    }

    public BigDecimal getEditTurnCredit() {
        return editTurnCredit;
    }

    public void setEditTurnCredit(BigDecimal editTurnCredit) {
        this.editTurnCredit = editTurnCredit;
    }

    public BigDecimal getEditCloseDebit() {
        return editCloseDebit;
    }

    public void setEditCloseDebit(BigDecimal editCloseDebit) {
        this.editCloseDebit = editCloseDebit;
    }

    public BigDecimal getEditCloseCredit() {
        return editCloseCredit;
    }

    public void setEditCloseCredit(BigDecimal editCloseCredit) {
        this.editCloseCredit = editCloseCredit;
    }

    @Override
    public Object getModel() {
        return account;
    }

    public String getText(Object object, String objectType) {
        if (objectType.equals("format.Number")) {
            return new DecimalFormat("#,###.##").format((BigDecimal) object);
        } else {
            return object.toString();
        }
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    //------------------------------------------------------------------------------------------
    private String getListOfPos() {
        String posString = "";
        userName = request.getSession().getAttribute("username").toString();
        reportGrade = Integer.parseInt(request.getSession().getAttribute("reportGrade").toString());
        if (selectedPos == null || selectedPos.trim().isEmpty()) {
            switch (reportGrade) {
                case 3:
                    posString = "000100";
                    this.consolidateFlag = "Y";
                    break;
                case 2:
                    posString = IMSRptDao.getPosOfUser(userName);
                    this.consolidateFlag = "Y";
                    break;
                default:
                    posString = IMSRptDao.getPosOfUser(userName);
                    this.consolidateFlag = "N";
                    break;
            }
        } else {
            String pos_cd;
            ArrayList<String> pos_stack = new ArrayList<>();
            ArrayList<String> listOfId
                    = (ArrayList<String>) DefineFun.string2Array(selectedPos, ",", 1);
            boolean isAdded;
            if (listOfId.size() > 0) {
                for (String id : listOfId) {
                    isAdded = false;
                    pos_cd = DefineFun.searchInTreeView(id, this.searchNodes);
                    for (String added_pos : pos_stack) {
                        if (added_pos.equals(pos_cd)) {
                            isAdded = true;
                            break;
                        }
                    }
                    if (!isAdded && pos_cd != null) {
                        posString += pos_cd + ",";
                        pos_stack.add(pos_cd);
                    }
                }
            }
            posString = posString.substring(0, posString.length() - 1);
            if (reportGrade == 3) {
                this.consolidateFlag = "Y";
            } else {
                this.consolidateFlag = "N";
            }
        }
        System.err.println(posString);
        return posString;
    }
}
