package vbsp.ims.restapi;

import java.util.List;

public class DuLieuPLNResp_T {

    private boolean isSuccess;
    private int code;
    private String message;
    private Meta_PLN meta;
    private List<DuLieuPLN_T> result;

    public boolean isIsSuccess() {
        return isSuccess;
    }

    public void setIsSuccess(boolean isSuccess) {
        this.isSuccess = isSuccess;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Meta_PLN getMeta() {
        return meta;
    }

    public void setMeta(Meta_PLN meta) {
        this.meta = meta;
    }

    public List<DuLieuPLN_T> getResult() {
        return result;
    }

    public void setResult(List<DuLieuPLN_T> result) {
        this.result = result;
    }

}
