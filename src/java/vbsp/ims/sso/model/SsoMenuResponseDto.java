package vbsp.ims.sso.model;

import java.util.List;
import java.util.Map;

public class SsoMenuResponseDto {

    private String user_id;
    private AppInfo application;
    private List<RoleItemDto> roles;

    // Getters & Setters
    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public AppInfo getApplication() {
        return application;
    }

    public void setApplication(AppInfo application) {
        this.application = application;
    }

    public List<RoleItemDto> getRoles() {
        return roles;
    }

    public void setRoles(List<RoleItemDto> roles) {
        this.roles = roles;
    }

    public static class AppInfo {

        private String client_id;

        public String getClient_id() {
            return client_id;
        }

        public void setClient_id(String client_id) {
            this.client_id = client_id;
        }
    }

    public static class RoleItemDto {

        private String role;
        // Sử dụng Map để hứng các resource động như "TTBC_3", "TTBC_4"
        private Map<String, ResourceDetailDto> resources;

        public String getRole() {
            return role;
        }

        public void setRole(String role) {
            this.role = role;
        }

        public Map<String, ResourceDetailDto> getResources() {
            return resources;
        }

        public void setResources(Map<String, ResourceDetailDto> resources) {
            this.resources = resources;
        }
    }

    public static class ResourceDetailDto {

        private List<String> actions;

        public List<String> getActions() {
            return actions;
        }

        public void setActions(List<String> actions) {
            this.actions = actions;
        }
    }
}
