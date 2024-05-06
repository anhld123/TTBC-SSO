/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vbsp.ims.define;

/**
 *
 * @author HP
 */
public class GenericResult<T> {

    private boolean isSuccess;
    private int code;
    private String message;
    private T result;

    public GenericResult() {}

    public GenericResult(T result){
        this.result = result;
        this.code = StatusCode.OK;
        this.isSuccess = true;
    }
    
    public GenericResult(T result, int code)    
    {
        this.result = result;
        this.code = code;
        this.message = "";
        if (this.code == StatusCode.OK)
            this.isSuccess = true;
    }
   
    public GenericResult(T result, int code, String message)
    {
        this.result = result;
        this.message = message;
        this.code = code;
        if (this.code == StatusCode.OK)
            this.isSuccess = true;
    }
    
    public GenericResult(int code, String message)
    {
        this.message = message;
        this.code = code;
        if (this.code == StatusCode.OK)
            this.isSuccess = true;
    }
    
    public GenericResult<T> Fail(String message)
    {
        return new GenericResult<T>(400, message);
    }

    public GenericResult<T> Fail(String message, int code)
    {
        return new GenericResult<T>(code, message);
    }

    public GenericResult<T> Success(T answer)
    {
        return new GenericResult<T>(answer);
    }

    public boolean isIsSuccess() {
        return isSuccess;
    }

    public void setIsSuccess(boolean IsSuccess) {
        this.isSuccess = IsSuccess;
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

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }
 
    
    
}
