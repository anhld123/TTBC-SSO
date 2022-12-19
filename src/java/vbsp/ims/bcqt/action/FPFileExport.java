/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vbsp.ims.bcqt.action;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import vbsp.ims.log.CoreLogger;
import vbsp.ims.bcqt.dao.DaoBcqtMain;
import vbsp.ims.bcqt.model.CommisionFP;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author HP
 */
public class FPFileExport {
    
    DaoBcqtMain dao;
    
    public void export(String posCode, String reportDate, String fileName)
    {
        dao = new DaoBcqtMain();       
        List<CommisionFP> _lstFPTrans = dao.GetCommissionFP(posCode,reportDate);
        String _seperator = "~";
        try {                        
            Writer outfile = null;            
            outfile = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fileName), "UTF-8"));
            for(int i =0 ; i < _lstFPTrans.size(); i++)
            {
                String _line = "" 
                        + _lstFPTrans.get(i).getPosCode() + _seperator
                        + _lstFPTrans.get(i).getRefNo() + _seperator
                        + _lstFPTrans.get(i).getValDate() + _seperator
                        + _lstFPTrans.get(i).getAccountNo() + _seperator
                        + _lstFPTrans.get(i).getAccountPos() + _seperator
                        + _lstFPTrans.get(i).getFlag() + _seperator
                        + _lstFPTrans.get(i).getAmount() + _seperator
                        + _lstFPTrans.get(i).getRemark() + "~VND~";
                outfile.append(_line);
            }                           
            outfile.flush();
            outfile.close();
            
        } catch (Exception e) {
            CoreLogger.error(FPFileExport.class.getCanonicalName() + " loi export-> " + e.getMessage());
        }
    }
    
    
}
