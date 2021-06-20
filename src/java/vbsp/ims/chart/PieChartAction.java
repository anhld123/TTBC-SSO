/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vbsp.ims.chart;

import com.opensymphony.xwork2.ActionSupport;
import java.util.List;


/**
 *
 * @author Trung Nguyen
 */
public class PieChartAction extends ActionSupport{
    
    private static final long serialVersionUID = 7038588781945164147L;
    private String pieChartData;
    private final List<PieChartData.KeyValue> pieDataList;

    public PieChartAction() {
            this.pieDataList = PieChartData.getPieDataList();
    }

    public String getPieChartData() {
            if (pieChartData == null || pieChartData.trim().length() <= 0) {
                    populateData();
            }
            return pieChartData;
    }

    private void populateData() {
            StringBuilder stringBuilder = new StringBuilder();
            for (PieChartData.KeyValue pieData : pieDataList) {
                    stringBuilder.append("[");
                    stringBuilder.append("'");
                    stringBuilder.append(pieData.getKey());
                    stringBuilder.append("'");
                    stringBuilder.append(",");
                    stringBuilder.append(pieData.getValue());
                    stringBuilder.append("]");
                    stringBuilder.append(",");
            }
            pieChartData = stringBuilder.toString().substring(0, stringBuilder.toString().length() - 1);
    }

    @Override
    public String execute() throws Exception {
        return SUCCESS;
    }
}
