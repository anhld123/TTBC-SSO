/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vbsp.ims.tracuu;

import java.util.List;

/**
 *
 * @author Administrator
 */
public class Tracuu_getalldata {
    private Integer totalpage;
    private List<Tracuu_viewdata> lsview;

    public Tracuu_getalldata() {
    }

    public Tracuu_getalldata(Integer totalpage, List<Tracuu_viewdata> lsview) {
        this.totalpage = totalpage;
        this.lsview = lsview;
    }

    public Integer getTotalpage() {
        return totalpage;
    }

    public void setTotalpage(Integer totalpage) {
        this.totalpage = totalpage;
    }

    public List<Tracuu_viewdata> getLsview() {
        return lsview;
    }

    public void setLsview(List<Tracuu_viewdata> lsview) {
        this.lsview = lsview;
    }

    
}
