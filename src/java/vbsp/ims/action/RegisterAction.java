/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vbsp.ims.action;

import com.opensymphony.xwork2.ActionSupport;
import vbsp.ims.dao.IMSRptDao;
import vbsp.ims.define.DefineFun;
import vbsp.ims.encrypt.DES;

/**
 *
 * @author Trung
 */
public class RegisterAction extends ActionSupport {

    private String userCode = "";
    private String oldPassword = "";
    private String newPassword = "";
    private String confirmPassword = "";
    private String message = "";
    
    public String changePassword() {
        return "success";
    }

    public String updatePassword() throws Exception {
        if (!newPassword.equals(confirmPassword)) {
            System.err.println("Error: password is not matching");
            message = "Lỗi: Bạn chưa nhập mật khẩu hoặc mật khẩu không đúng";
        } else {
            if (userCode.isEmpty()) {
                System.err.println("Error: User code is null");
                //message = "Lỗi: User code is null";
            } else {
                String lcOldPass;
                lcOldPass = IMSRptDao.getPassword(userCode);
                String key = DefineFun.getKeyDes("1");   
                DES oldcrypt = new DES(key); 
                String encryptKey = oldcrypt.encrypt(oldPassword);                
                if (encryptKey.equals(lcOldPass)) {
//                    System.err.println("Key " + key);
//                    DES newcrypt = new DES(key); 
//                    System.err.println("'" + newPassword + "'");
//                    String newEncryptKey = newcrypt.encrypt(newPassword);
//                    System.err.println("'" + newEncryptKey + "'");
                    IMSRptDao.updatePassword(userCode, newPassword);
                    message = "Thông báo: Cập nhật mật khẩu thành công";
                } else {
                    System.err.println("Error: Old pass is not equal");
                    message = "Lỗi: Mật khẩu cũ nhập chưa đúng";
                }
            }           
        }
        return "success";
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }
    
}
