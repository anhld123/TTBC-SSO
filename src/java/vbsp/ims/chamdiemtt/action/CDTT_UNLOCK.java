package vbsp.ims.chamdiemtt.action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.apache.struts2.ServletActionContext;
import vbsp.ims.chamdiemtt.dao.DaoChamdiemttMain;
import vbsp.ims.log.CoreLogger;

public class CDTT_UNLOCK extends ActionChamdiemttMain implements CdttFunction {
   public String load() {
      try {
         System.err.println("CDTT_UNLOCK");
         if (!this.getParaSession()) {
            return "error";
         } else {
            HashMap hmParameter = this.getParameter();
            DaoChamdiemttMain daoMain = new DaoChamdiemttMain();
            List<String> lstPos = (List)hmParameter.get("poscd");
            String _reportDate = hmParameter.get("ngay_bc").toString();
            if (lstPos != null && lstPos.size() != 0) {
               this.lstDulieuNt = daoMain.getBranchLockStatus(lstPos, _reportDate);
               return "success";
            } else {
               this.addActionError("Bạn chưa chọn đơn vị!");
               return "error";
            }
         }
      } catch (Exception var5) {
         CoreLogger.error(this.getClass().getName() + " Exception -> CDTT_UNLOCK.load() " + var5.getMessage());
         System.err.println(this.getClass().getName() + " Exception -> CDTT_UNLOCK.load() " + var5.getMessage());
         this.addActionError("Có lỗi xảy ra: " + var5.getMessage());
         return "error";
      }
   }

   public String save() {
      try {
         String _branchCode = ServletActionContext.getRequest().getParameter("branchCode");
         String _status = ServletActionContext.getRequest().getParameter("status");
         String _reportDate = ServletActionContext.getRequest().getParameter("reportDate");
         String _oracleDate = (new SimpleDateFormat("dd-MMM-yyyy")).format((new SimpleDateFormat("dd/MM/yyyy")).parse(_reportDate));
         DaoChamdiemttMain daoMain = DaoChamdiemttMain.newInstance();
         List<String> lstPos = new ArrayList();
         lstPos.add(_branchCode);
         daoMain.unlockLockStatus(lstPos, _oracleDate, _status);
         return "success";
      } catch (Exception var7) {
         CoreLogger.error(this.getClass().getName() + " Exception -> save CDTT_UNLOCK: " + var7.getMessage());
         System.err.println(this.getClass().getName() + " Exception -> save CDTT_UNLOCK: " + var7.getMessage());
         this.addActionError("Có lỗi xảy ra: " + var7.getMessage());
         return "error";
      }
   }
}