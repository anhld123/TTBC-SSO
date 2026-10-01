package vbsp.ims.dao.khnv;

import com.opensymphony.xwork2.ActionContext;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import vbsp.ims.log.CoreLogger;
import vbsp.ims.model.ktnb.PosMainModel;
import vbsp.ims.report.fast.ListValue;
import vbsp.ims.restapi.DuLieuNTService;
import vbsp.ims.restapi.ListCommune;

/**
 * @author BAOANH
 */
public class DaoListPosFromUser {

    private List<ListCommune> lstXa_API;
    private DuLieuNTService _serverAPI = new DuLieuNTService();

    public List<ListCommune> getLstXa_API() {
        return lstXa_API;
    }

    public void setLstXa_API(List<ListCommune> lstXa_API) {
        this.lstXa_API = lstXa_API;
    }

    public DuLieuNTService getServerAPI() {
        return _serverAPI;
    }

    public void setServerAPI(DuLieuNTService _serverAPI) {
        this._serverAPI = _serverAPI;
    }

   
    public PosMainModel get_pos_main_pos(String userId, String capbc) {
        PosMainModel posMainModel = new PosMainModel();
        
        String sessionPosCode = "";
        String sessionMainPosCode = "";

        try {
            // Tự động lấy Session từ ActionContext của Struts 2 mà không cần truyền tham số từ ngoài vào
            Map<String, Object> session = ActionContext.getContext().getSession();
            if (session != null) {
                sessionPosCode = (String) session.get("POS_CODE");
                sessionMainPosCode = (String) session.get("MA_CN");
            }
        } catch (Exception e) {
            System.err.println("Warning: Không thể lấy session trong DAO (có thể chạy ngoài luồng Web): " + e.getMessage());
        }

        posMainModel.setPosCd(sessionPosCode != null ? sessionPosCode : "");
        posMainModel.setMainPosCd(sessionMainPosCode != null ? sessionMainPosCode : "");

        try {
            if (sessionPosCode != null && !sessionPosCode.isEmpty()) {
                lstXa_API = _serverAPI.getListXa("", "", "", sessionPosCode);
            } else {
                lstXa_API = new ArrayList<>();
            }

            List<ListValue> lstdata = new ArrayList<>();
            if (lstXa_API != null) {
                int stt = 1;
                for (ListCommune item : lstXa_API) {
                    String maXa = item.getCommuneCode(); 
                    String tenXa = item.getCommuneName();
                    
                    // Định dạng hiển thị: MA -> TEN
                    String displayTen = maXa + " -> " + tenXa;
                    
                    lstdata.add(new ListValue(String.valueOf(stt), displayTen, maXa));
                    stt++;
                }
            }
            posMainModel.setLstXa(lstdata);

        } catch (Exception e) {
            System.err.println("Loi khi goi API lay danh sach xa trong get_pos_main_pos: " + e.getMessage());
            CoreLogger.error(this.getClass().getName() + " get_pos_main_pos API -> " + e.getMessage());
        }

        return posMainModel;
    }
}