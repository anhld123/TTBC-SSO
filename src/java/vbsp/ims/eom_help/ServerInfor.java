/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vbsp.ims.eom_help;

/**
 *
 * @author TrungNguyen
 */
public class ServerInfor {
    
    private String serverID;
    private String serverName;
    private String serverAddress;
    private int numofjob;
    private String jobdetail;
    
    
    public ServerInfor(){
        serverID = "UNKNOWN";
    }
    
    public ServerInfor(
            String serverID,
            String serverName,
            String serverAddress,
            int numofjob,
            String jobdetail){
        this.serverID = serverID;
        this.serverName = serverName;
        this.serverAddress = serverAddress;
        this.numofjob = numofjob;
        this.jobdetail = jobdetail;
    }

    public String getServerID() {
        return serverID;
    }

    public void setServerID(String serverID) {
        this.serverID = serverID;
    }

    public String getServerName() {
        return serverName;
    }

    public void setServerName(String serverName) {
        this.serverName = serverName;
    }

    public String getServerAddress() {
        return serverAddress;
    }

    public void setServerAddress(String serverAddress) {
        this.serverAddress = serverAddress;
    }

    public int getNumofjob() {
        return numofjob;
    }

    public void setNumofjob(int numofjob) {
        this.numofjob = numofjob;
    }

    public String getJobdetail() {
        return jobdetail;
    }

    public void setJobdetail(String jobdetail) {
        this.jobdetail = jobdetail;
    }
    
    
}
