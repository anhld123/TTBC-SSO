package vbsp.ims.restapi;

public class DuLieuPLNResp {
    private boolean isSuccess;
    private float code;
    private String message;
    private DuLieuPLN result;  

    // Getter / Setter
    public boolean isSuccess() {
        return isSuccess;
    }

    public void setSuccess(boolean success) {
        isSuccess = success;
    }

    public float getCode() {
        return code;
    }

    public void setCode(float code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public DuLieuPLN getResult() {
        return result;
    }

    public void setResult(DuLieuPLN result) {
        this.result = result;
    }
}
