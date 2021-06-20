/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vbsp.ims.chart;

import com.opensymphony.xwork2.ActionSupport;
import java.util.List;
import com.google.gson.Gson;
import java.util.ArrayList;


/**
 *
 * @author Trung Nguyen
 */
public class AjaxActions extends ActionSupport {
    
    private ArrayList dataTable;
    private String jsonData;
    private String title;
    private int chartWidth ;
    private int chartHeight;
    private boolean is3D;
    private boolean showColorCode;
      

    public String getData() {
        
        List<PieChartData.KeyValue> pieDataList = PieChartData.getPieDataList();
        
        dataTable = new ArrayList();
        
        ArrayList header = new ArrayList();
        header.add( "Country");
        header.add( "Area(square km)");
        
        dataTable.add(header);              
        
        for (PieChartData.KeyValue pieData : pieDataList) {            
            ArrayList row = new ArrayList();
            row.add(pieData.key);
            row.add( Integer.parseInt(pieData.value));
            dataTable.add(row);
        }                
        
        // Create a new instance of Gson
        Gson gson = new Gson();
        jsonData = gson.toJson(dataTable);    
        return ActionSupport.SUCCESS;
    }        

    public String getJsonData() {
        return jsonData;
    }

    public void setJsonData(String jsonData) {
        this.jsonData = jsonData;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getChartWidth() {
        return chartWidth;
    }

    public void setChartWidth(int chartWidth) {
        this.chartWidth = chartWidth;
    }

    public int getChartHeight() {
        return chartHeight;
    }

    public void setChartHeight(int chartHeight) {
        this.chartHeight = chartHeight;
    }
    

    public boolean isIs3D() {
        return is3D;
    }

    public void setIs3D(boolean is3D) {
        this.is3D = is3D;
    }

    public boolean isShowColorCode() {
        return showColorCode;
    }

    public void setShowColorCode(boolean showColorCode) {
        this.showColorCode = showColorCode;
    }    
}
