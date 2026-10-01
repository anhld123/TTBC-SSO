/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vbsp.ims.sso;

import java.util.ArrayList;

/**
 *
 * @author admin
 */
public class MenuDataDto {

    private String menuId;
    private String text;
    private String description;
    private Long parentId;
    private String navigateUrl;

    public MenuDataDto() {
    }

    public String getMenuId() {
        return menuId;
    }

    public void setMenuId(Object menuId) {
        if (menuId != null) {
            this.menuId = menuId.toString().trim();
        } else {
            this.menuId = null;
        }
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getNavigateUrl() {
        return navigateUrl;
    }

    public void setNavigateUrl(String navigateUrl) {
        this.navigateUrl = navigateUrl;
    }

    public static class MenuDataDtoResp {

        public boolean isSuccess;
        public int code;
        public String message;
        public ArrayList<MenuDataDto> result;

        public MenuDataDtoResp() {
        }

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

        public ArrayList<MenuDataDto> getResult() {
            return result;
        }

        public void setResult(ArrayList<MenuDataDto> result) {
            this.result = result;
        }
    }
}
