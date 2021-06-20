/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package vbsp.ims.syn;

import java.io.File;
import java.util.List;
import vbsp.ims.define.Define;
import vbsp.ims.define.DefineFun;
import vbsp.ims.log.CoreLogger;
import vbsp.ims.xml.ImsReadWriteXmlFile;

/**
 *
 * @author LION
 */
public class ReSendXmlWebSevices {
    public boolean SendFileToWebServices(File file)
    {
        boolean bSuccess=false;
        //Khoi tao lop de convet file ve kieu byte
        ImsReadWriteXmlFile fileToByte = new ImsReadWriteXmlFile();
        //Khoi tao lop de gui du lieu cho web services
        ProcessReportSyn sendWebServices = new ProcessReportSyn();
        try {
            //Neu khong tim thay file thi return
            if(!file.exists())
            {
                System.err.println("Khong co file can gui");
                 CoreLogger.error(this.getClass().getName()+" Khong co file de gui Loi ham SendFileToWebServices ");
            }
            //Dua file ve kieu byte
            byte[] outbytexml = fileToByte.readFileByte(file.getCanonicalPath());
            
            //Gui cho web services
            String strStatus = sendWebServices.ReceivesXmlFile(outbytexml, file.getName());
            //Neu trang thai tra ve la thanh cong thi xoa file xml di
             if (strStatus.equals(Define.WEB_SERVICES_STATUS_FAIL)) {
                System.err.println("Ban chua dong bo du lieu duoc ve TW");
//                setMessage("Lỗi bạn chưa gửi dữ liệu được về trung ương ");
                if (file.exists()) {
//                    checkfile.delete();
                }
            } else if (strStatus.equals(Define.WEB_SERVICES_STATUS_OK)) {
//                setMessage("Bạn gửi dữ liệu về trung ương thành công");
                if (file.exists()) {
                    file.delete();
                }
            } else {
//                setMessage("Bạn không thể gửi dữ liệu lên trung ương do bị khóa </br>Xin gọi điện về ban rủi ro để được gửi lại số liệu ! ");
                if (file.exists()) {
                    file.delete();
                }
                return true;
            }
//            if (strStatus.equals(Define.WEB_SERVICES_STATUS_OK)) {
//                file.delete();
//                bSuccess = true;
//            } else {
//                bSuccess = false;
//            }
        } catch (Exception e) {
            CoreLogger.error(this.getClass().getName()+" Ham loi SendFileToWebServices "+e.getMessage());
        }
        return bSuccess;
    }
    
    public void scanFileXmlSendWebServices(String strPath)
    {
        if(!strPath.endsWith("/"))
            strPath+="/";
        
        List<File> listFile = DefineFun.listfilexml(strPath);
        if(listFile.isEmpty())
            return;
        
        for(File file: listFile)
        {
            if(file.getName().toUpperCase().endsWith("XML"))
                SendFileToWebServices(file);
            
        }
    }
    
}
