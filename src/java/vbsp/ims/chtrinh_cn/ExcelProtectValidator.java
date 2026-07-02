/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vbsp.ims.chtrinh_cn;

/**
 *
 * @author admin
 */
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.lang.reflect.Method;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.poifs.crypt.CryptoFunctions;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFSheet;

public class ExcelProtectValidator {

    // Password bắt buộc
    private static final String REQUIRED_PASSWORD = "khnv3112ducanh";

    /**
     * Kiểm tra file Excel có hợp lệ để upload hay không. Điều kiện: - File phải
     * là Excel đọc được - Tất cả sheet phải được protect - Password protect của
     * tất cả sheet phải là REQUIRED_PASSWORD
     *
     * @param file file upload tạm
     * @return ValidationResult chứa trạng thái và thông báo
     */
    public static ValidationResult validateExcelFile(File file) {
        if (file == null || !file.exists()) {
            return new ValidationResult(false, "File upload không tồn tại.");
        }

        InputStream is = null;
        Workbook workbook = null;

        try {
            is = new FileInputStream(file);
            workbook = WorkbookFactory.create(is);

            if (workbook.getNumberOfSheets() <= 0) {
                return new ValidationResult(false, "File Excel không có sheet.");
            }

            for (int i = 0; i < workbook.getNumberOfSheets(); i++) {
                Sheet sheet = workbook.getSheetAt(i);

                ValidationResult rs = validateSheet(sheet, REQUIRED_PASSWORD);
                if (!rs.isSuccess()) {
                    return new ValidationResult(
                            false,
                            "Sheet [" + sheet.getSheetName() + "] không hợp lệ: " + rs.getMessage()
                    );
                }
            }

            return new ValidationResult(true, "File hợp lệ, được phép upload.");

        } catch (Exception e) {
            e.printStackTrace();
            return new ValidationResult(false, "File không đúng định dạng Excel hoặc bị lỗi đọc file: " + e.getMessage());
        } finally {
            try {
                if (workbook != null) {
                    workbook.close();
                }
            } catch (Exception e) {
            }

            try {
                if (is != null) {
                    is.close();
                }
            } catch (Exception e) {
            }
        }
    }

    /**
     * Kiểm tra 1 sheet có được protect bằng đúng password hay không
     */
    private static ValidationResult validateSheet(Sheet sheet, String requiredPassword) {
        if (sheet == null) {
            return new ValidationResult(false, "Sheet null.");
        }

        try {
            // =========================================================
            // 1. XỬ LÝ FILE .XLSX
            // =========================================================
            if (sheet instanceof XSSFSheet) {
                XSSFSheet xssfSheet = (XSSFSheet) sheet;

                // Nếu sheet chưa protect => không hợp lệ
                if (!isXlsxSheetProtected(xssfSheet)) {
                    return new ValidationResult(false, "File excel không đúng định dạng file gốc.");
                }

                boolean validPassword = xssfSheet.validateSheetPassword(requiredPassword);
                if (!validPassword) {
                    return new ValidationResult(false, "File excel không đúng định dạng file gốc.");
                }

                return new ValidationResult(true, "OK");
            }

            // =========================================================
            // 2. XỬ LÝ FILE .XLS
            // =========================================================
            if (sheet instanceof HSSFSheet) {
                HSSFSheet hssfSheet = (HSSFSheet) sheet;

                HssfProtectInfo protectInfo = getHssfProtectInfo(hssfSheet);

                if (!protectInfo.isProtected()) {
                    return new ValidationResult(false, "File excel không đúng định dạng file gốc.");
                }

                int targetHash = CryptoFunctions.createXorVerifier1(requiredPassword);

                if ((protectInfo.getPasswordHash() & 0xFFFF) != (targetHash & 0xFFFF)) {
                    return new ValidationResult(false, "File excel không đúng định dạng file gốc.");
                }

                return new ValidationResult(true, "OK");
            }

            return new ValidationResult(false, "Sheet không thuộc định dạng hỗ trợ.");

        } catch (Exception e) {
            e.printStackTrace();
            return new ValidationResult(false, "Lỗi khi kiểm tra sheet: " + e.getMessage());
        }
    }

    /**
     * Kiểm tra sheet .xlsx có đang protect hay không
     */
    private static boolean isXlsxSheetProtected(XSSFSheet xssfSheet) {
        try {
            if (xssfSheet == null || xssfSheet.getCTWorksheet() == null) {
                return false;
            }

            if (!xssfSheet.getCTWorksheet().isSetSheetProtection()) {
                return false;
            }

            // Có thẻ protection nhưng chưa chắc đã bật sheet protect thật
            // kiểm tra thêm thuộc tính sheet
            if (xssfSheet.getCTWorksheet().getSheetProtection() == null) {
                return false;
            }

            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Đọc thông tin protect của sheet .xls bằng Reflection
     */
    private static HssfProtectInfo getHssfProtectInfo(HSSFSheet hssfSheet) throws Exception {
        HssfProtectInfo info = new HssfProtectInfo(false, 0);

        // Lấy đối tượng Sheet nội bộ của HSSF
        Method getSheetMethod = HSSFSheet.class.getDeclaredMethod("getSheet");
        getSheetMethod.setAccessible(true);
        Object internalSheet = getSheetMethod.invoke(hssfSheet);

        if (internalSheet == null) {
            return info;
        }

        // Lấy ProtectionBlock
        Method getProtectionBlockMethod = internalSheet.getClass().getDeclaredMethod("getProtectionBlock");
        getProtectionBlockMethod.setAccessible(true);
        Object protectionBlock = getProtectionBlockMethod.invoke(internalSheet);

        if (protectionBlock == null) {
            return info;
        }

        // Đọc cờ protect sheet
        boolean isProtected = false;
        try {
            Method isProtectedMethod = protectionBlock.getClass().getDeclaredMethod("isSheetProtected");
            isProtectedMethod.setAccessible(true);
            Object obj = isProtectedMethod.invoke(protectionBlock);
            if (obj instanceof Boolean) {
                isProtected = ((Boolean) obj).booleanValue();
            }
        } catch (NoSuchMethodException e) {
            // Nếu version POI không có method isSheetProtected thì vẫn tiếp tục
            // và suy luận qua password hash
        }

        // Đọc password hash
        int passwordHash = 0;
        try {
            Method getPasswordHashMethod = protectionBlock.getClass().getDeclaredMethod("getPasswordHash");
            getPasswordHashMethod.setAccessible(true);
            Object obj = getPasswordHashMethod.invoke(protectionBlock);
            if (obj instanceof Integer) {
                passwordHash = ((Integer) obj).intValue();
            }
        } catch (NoSuchMethodException e) {
            passwordHash = 0;
        }

        // Nếu chưa đọc được cờ protect nhưng có password hash thì coi như đang protect
        if (!isProtected && passwordHash != 0) {
            isProtected = true;
        }

        info.setProtected(isProtected);
        info.setPasswordHash(passwordHash);

        return info;
    }

    // =========================================================
    // CLASS KẾT QUẢ VALIDATE
    // =========================================================
    public static class ValidationResult {

        private boolean success;
        private String message;

        public ValidationResult() {
        }

        public ValidationResult(boolean success, String message) {
            this.success = success;
            this.message = message;
        }

        public boolean isSuccess() {
            return success;
        }

        public void setSuccess(boolean success) {
            this.success = success;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }

    // =========================================================
    // CLASS CHỨA THÔNG TIN PROTECT CỦA HSSF
    // =========================================================
    private static class HssfProtectInfo {

        private boolean isProtected;
        private int passwordHash;

        public HssfProtectInfo(boolean isProtected, int passwordHash) {
            this.isProtected = isProtected;
            this.passwordHash = passwordHash;
        }

        public boolean isProtected() {
            return isProtected;
        }

        public void setProtected(boolean isProtected) {
            this.isProtected = isProtected;
        }

        public int getPasswordHash() {
            return passwordHash;
        }

        public void setPasswordHash(int passwordHash) {
            this.passwordHash = passwordHash;
        }
    }
}
