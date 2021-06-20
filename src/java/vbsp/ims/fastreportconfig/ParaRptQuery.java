/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package vbsp.ims.fastreportconfig;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Trung
 */
@Entity
@Table(name = "PARA_RPT_QUERY")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ParaRptQuery.findAll", query = "SELECT p FROM ParaRptQuery p"),
    @NamedQuery(name = "ParaRptQuery.findByParaOrder", query = "SELECT p FROM ParaRptQuery p WHERE p.paraOrder = :paraOrder"),
    @NamedQuery(name = "ParaRptQuery.findByParaKey", query = "SELECT p FROM ParaRptQuery p WHERE p.paraKey = :paraKey"),
    @NamedQuery(name = "ParaRptQuery.findByParaDesc", query = "SELECT p FROM ParaRptQuery p WHERE p.paraDesc = :paraDesc"),
    @NamedQuery(name = "ParaRptQuery.findByParaType", query = "SELECT p FROM ParaRptQuery p WHERE p.paraType = :paraType"),
    @NamedQuery(name = "ParaRptQuery.findByParaTable", query = "SELECT p FROM ParaRptQuery p WHERE p.paraTable = :paraTable"),
    @NamedQuery(name = "ParaRptQuery.findByParaColDesc", query = "SELECT p FROM ParaRptQuery p WHERE p.paraColDesc = :paraColDesc"),
    @NamedQuery(name = "ParaRptQuery.findByParaColValue", query = "SELECT p FROM ParaRptQuery p WHERE p.paraColValue = :paraColValue"),
    @NamedQuery(name = "ParaRptQuery.findByParaFilter", query = "SELECT p FROM ParaRptQuery p WHERE p.paraFilter = :paraFilter"),
    @NamedQuery(name = "ParaRptQuery.findByParaSort", query = "SELECT p FROM ParaRptQuery p WHERE p.paraSort = :paraSort"),
    @NamedQuery(name = "ParaRptQuery.findByMkrId", query = "SELECT p FROM ParaRptQuery p WHERE p.mkrId = :mkrId"),
    @NamedQuery(name = "ParaRptQuery.findByMkrDt", query = "SELECT p FROM ParaRptQuery p WHERE p.mkrDt = :mkrDt")})
public class ParaRptQuery implements Serializable {
    private static final long serialVersionUID = 1L;
    @Column(name = "PARA_ORDER")
    private int paraOrder;
    @Id
    @Basic(optional = false)
    @Column(name = "PARA_KEY")
    private String paraKey;
    @Column(name = "PARA_DESC")
    private String paraDesc;
    @Column(name = "PARA_TYPE")
    private String paraType;
    @Column(name = "PARA_TABLE")
    private String paraTable;
    @Column(name = "PARA_COL_DESC")
    private String paraColDesc;
    @Column(name = "PARA_COL_VALUE")
    private String paraColValue;
    @Column(name = "PARA_FILTER")
    private String paraFilter;
    @Column(name = "PARA_SORT")
    private String paraSort;
    @Column(name = "MKR_ID")
    private String mkrId;
    @Column(name = "MKR_DT")    
    private String mkrDt;

    public ParaRptQuery() {
    }

    public ParaRptQuery(String paraKey) {
        this.paraKey = paraKey;
    }

    public int getParaOrder() {
        return paraOrder;
    }

    public void setParaOrder(int paraOrder) {
        this.paraOrder = paraOrder;
    }

    public String getMkrDt() {
        return mkrDt;
    }

    public void setMkrDt(String mkrDt) {
        this.mkrDt = mkrDt;
    }

    

    public String getParaKey() {
        return paraKey;
    }

    public void setParaKey(String paraKey) {
        this.paraKey = paraKey;
    }

    public String getParaDesc() {
        return paraDesc;
    }

    public void setParaDesc(String paraDesc) {
        this.paraDesc = paraDesc;
    }

    public String getParaType() {
        return paraType;
    }

    public void setParaType(String paraType) {
        this.paraType = paraType;
    }

    public String getParaTable() {
        return paraTable;
    }

    public void setParaTable(String paraTable) {
        this.paraTable = paraTable;
    }

    public String getParaColDesc() {
        return paraColDesc;
    }

    public void setParaColDesc(String paraColDesc) {
        this.paraColDesc = paraColDesc;
    }

    public String getParaColValue() {
        return paraColValue;
    }

    public void setParaColValue(String paraColValue) {
        this.paraColValue = paraColValue;
    }

    public String getParaFilter() {
        return paraFilter;
    }

    public void setParaFilter(String paraFilter) {
        this.paraFilter = paraFilter;
    }

    public String getParaSort() {
        return paraSort;
    }

    public void setParaSort(String paraSort) {
        this.paraSort = paraSort;
    }

    public String getMkrId() {
        return mkrId;
    }

    public void setMkrId(String mkrId) {
        this.mkrId = mkrId;
    }


    @Override
    public int hashCode() {
        int hash = 0;
        hash += (paraKey != null ? paraKey.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ParaRptQuery)) {
            return false;
        }
        ParaRptQuery other = (ParaRptQuery) object;
        if ((this.paraKey == null && other.paraKey != null) || (this.paraKey != null && !this.paraKey.equals(other.paraKey))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "vbsp.ims.fastreportconfig.ParaRptQuery[ paraKey=" + paraKey + " ]";
    }
    
}
