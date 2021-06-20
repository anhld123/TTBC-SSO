/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package vbsp.ims.fastreportconfig;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Trung
 */
@Entity
@Table(name = "GROUP_QUERY")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "GroupQuery.findAll", query = "SELECT g FROM GroupQuery g"),
    @NamedQuery(name = "GroupQuery.findByGroupId", query = "SELECT g FROM GroupQuery g WHERE g.groupQueryPK.groupId = :groupId"),
    @NamedQuery(name = "GroupQuery.findByGroupDesc", query = "SELECT g FROM GroupQuery g WHERE g.groupDesc = :groupDesc"),
    @NamedQuery(name = "GroupQuery.findByGroupFlg", query = "SELECT g FROM GroupQuery g WHERE g.groupFlg = :groupFlg"),
    @NamedQuery(name = "GroupQuery.findByGroupOrder", query = "SELECT g FROM GroupQuery g WHERE g.groupOrder = :groupOrder"),
    @NamedQuery(name = "GroupQuery.findByModule", query = "SELECT g FROM GroupQuery g WHERE g.groupQueryPK.module = :module"),
    @NamedQuery(name = "GroupQuery.findByApplyRegion", query = "SELECT g FROM GroupQuery g WHERE g.groupQueryPK.applyRegion = :applyRegion"),
    @NamedQuery(name = "GroupQuery.findByMkrId", query = "SELECT g FROM GroupQuery g WHERE g.mkrId = :mkrId"),
    @NamedQuery(name = "GroupQuery.findByMkrDt", query = "SELECT g FROM GroupQuery g WHERE g.mkrDt = :mkrDt")})
public class GroupQuery implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected GroupQueryPK groupQueryPK;
    @Column(name = "GROUP_DESC")
    private String groupDesc;
    @Column(name = "GROUP_FLG")
    private String groupFlg;
    @Column(name = "GROUP_ORDER")
    private int groupOrder;
    @Column(name = "MKR_ID")
    private String mkrId;
    @Column(name = "MKR_DT")    
    private String mkrDt;

    public GroupQuery() {
    }

    public GroupQuery(GroupQueryPK groupQueryPK) {
        this.groupQueryPK = groupQueryPK;
    }

    public GroupQuery(String groupId, String module, String applyRegion) {
        this.groupQueryPK = new GroupQueryPK(groupId, module, applyRegion);
    }

    public GroupQueryPK getGroupQueryPK() {
        return groupQueryPK;
    }

    public void setGroupQueryPK(GroupQueryPK groupQueryPK) {
        this.groupQueryPK = groupQueryPK;
    }

    public String getGroupDesc() {
        return groupDesc;
    }

    public void setGroupDesc(String groupDesc) {
        this.groupDesc = groupDesc;
    }

    public int getGroupOrder() {
        return groupOrder;
    }

    public void setGroupOrder(int groupOrder) {
        this.groupOrder = groupOrder;
    }
    

    public String getMkrId() {
        return mkrId;
    }

    public void setMkrId(String mkrId) {
        this.mkrId = mkrId;
    }

    public String getGroupFlg() {
        return groupFlg;
    }

    public void setGroupFlg(String groupFlg) {
        this.groupFlg = groupFlg;
    }

    public String getMkrDt() {
        return mkrDt;
    }

    public void setMkrDt(String mkrDt) {
        this.mkrDt = mkrDt;
    }

    

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (groupQueryPK != null ? groupQueryPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof GroupQuery)) {
            return false;
        }
        GroupQuery other = (GroupQuery) object;
        if ((this.groupQueryPK == null && other.groupQueryPK != null) || (this.groupQueryPK != null && !this.groupQueryPK.equals(other.groupQueryPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "vbsp.ims.fastreportconfig.GroupQuery[ groupQueryPK=" + groupQueryPK + " ]";
    }
    
}
