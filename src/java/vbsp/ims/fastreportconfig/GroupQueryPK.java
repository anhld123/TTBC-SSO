/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package vbsp.ims.fastreportconfig;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author Trung
 */
@Embeddable
public class GroupQueryPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "GROUP_ID")
    private String groupId;
    @Basic(optional = false)
    @Column(name = "MODULE")
    private String module;
    @Basic(optional = false)
    @Column(name = "APPLY_REGION")
    private String applyRegion;

    public GroupQueryPK() {
    }

    public GroupQueryPK(String groupId, String module, String applyRegion) {
        this.groupId = groupId;
        this.module = module;
        this.applyRegion = applyRegion;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module;
    }

    public String getApplyRegion() {
        return applyRegion;
    }

    public void setApplyRegion(String applyRegion) {
        this.applyRegion = applyRegion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (groupId != null ? groupId.hashCode() : 0);
        hash += (module != null ? module.hashCode() : 0);
        hash += (applyRegion != null ? applyRegion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof GroupQueryPK)) {
            return false;
        }
        GroupQueryPK other = (GroupQueryPK) object;
        if ((this.groupId == null && other.groupId != null) || (this.groupId != null && !this.groupId.equals(other.groupId))) {
            return false;
        }
        if ((this.module == null && other.module != null) || (this.module != null && !this.module.equals(other.module))) {
            return false;
        }
        if ((this.applyRegion == null && other.applyRegion != null) || (this.applyRegion != null && !this.applyRegion.equals(other.applyRegion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "vbsp.ims.fastreportconfig.GroupQueryPK[ groupId=" + groupId + ", module=" + module + ", applyRegion=" + applyRegion + " ]";
    }
    
}
