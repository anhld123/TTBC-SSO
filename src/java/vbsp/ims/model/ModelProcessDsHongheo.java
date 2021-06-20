/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vbsp.ims.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import vbsp.ims.core.DataTable;
import vbsp.ims.core.MappingClassValue;
import vbsp.ims.core.RowField;
import vbsp.ims.dao.DaoDsHongheo;
import vbsp.ims.log.CoreLogger;
import vbsp.ims.reader.ReaderFileExcel;
import vbsp.ims.xml.ImsException;

/**
 *
 * @author LION
 */
public class ModelProcessDsHongheo {

    public boolean isCheckData(DataTable table) {
        boolean bSuccess = true;
        try {
            Map<Integer, Integer> map = new HashMap<>();
            for (int i = 0; i < table.size(); i++) {
                if (!map.containsKey(table.get(i).size())) {
                    map.put(table.get(i).size(), i);
                }
            }
            if (map.size() > 1) {
                bSuccess = false;
            }
        } catch (Exception e) {
            CoreLogger.error(this.getClass().getCanonicalName() + " isCheckData->Exception-> " + e.getMessage());
            System.err.println(this.getClass().getCanonicalName() + " isCheckData->Exception-> " + e.getMessage());
            bSuccess = false;
        }
        return bSuccess;
    }

    public Map<Integer, RowField> getCheckDataError(DataTable table) {
        Map<Integer, RowField> mapOut = new HashMap<>();
        try {
            Map<Integer, Integer> map = new HashMap<>();
            for (int i = 0; i < table.size(); i++) {
                if (!map.containsKey(table.get(i).size())) {
                    map.put(table.get(i).size(), table.get(i).size());
                    mapOut.put(i, table.get(i));
                }
            }
            if (map.size() > 0) {
                mapOut.remove(0);
            }
        } catch (Exception e) {
            CoreLogger.error(this.getClass().getCanonicalName() + " getCheckDataError->Exception-> " + e.getMessage());
            System.err.println(this.getClass().getCanonicalName() + " getCheckDataError->Exception-> " + e.getMessage());

        }
        return mapOut;
    }
    public List<ModelDsHongheo> getTableviewInsert(List lstObj) throws Exception
    {
        List<ModelDsHongheo> lst = new ArrayList<>();
        try {
            int index = lstObj.size() < 10 ? lstObj.size() : 10;
            for(int i=0;i<index;i++)
            {
                ModelDsHongheo value = (ModelDsHongheo)lstObj.get(i);
                lst.add(value);
            }
        } catch (Exception e) {
            throw new Exception("loi trong ham getTableviewInsert ",e);
        }
        return lst;
    }
    public List<ModelDsHongheo> getDsHongheo(DataTable table) {
        List<ModelDsHongheo> lst = new ArrayList<>();
        try {
            int index = table.size() < 10 ? table.size() : 10;
            for (int i = 0; i < index; i++) {
                RowField rowfield = table.get(i);
//            for (int j = 0; j < rowfield.size(); j++) {
                ModelDsHongheo value = new ModelDsHongheo();
                value.setMatinh(rowfield.get(0).getValueAsString());
                value.setMahuyen(rowfield.get(1).getValueAsString());
                value.setMaxa(rowfield.get(2).getValueAsString());
                value.setMathon(rowfield.get(3).getValueAsString());
                value.setTenkh(rowfield.get(4).getValueAsString());
                value.setGioitinh(rowfield.get(5).getValueAsString());
                value.setNgaysinh(rowfield.get(6).getValueAsString());
                value.setSocmt(rowfield.get(7).getValueAsString());
                value.setNgaycap(rowfield.get(8).getValueAsString());
                value.setNoicap(rowfield.get(9).getValueAsString());
                value.setDantoc(rowfield.get(10).getValueAsString());
                value.setLoai_Kh(rowfield.get(11).getValueAsString());
                value.setNgayloai(rowfield.get(12).getValueAsString());
                lst.add(value);
//            }
            }
        } catch (Exception e) {
            CoreLogger.error(this.getClass().getCanonicalName() + " getDsHongheo->Exception-> " + e.getMessage());
            System.err.println(e.getMessage());
        }

        return lst;
    }

    public List<Object> getDataMapColumnTable(DataTable table, HashMap<Integer, ModelMapping> hmMap) {
        List<Object> lstObj = new ArrayList<>();
        try {
            for (int i = 0; i < table.size(); i++) {
                lstObj.add(getDataMapColumnRowField(table.get(i), hmMap));
            }
        } catch (Exception e) {
            CoreLogger.error(this.getClass().getCanonicalName() + " getDataMapColumnTable->Exception-> " + e.getMessage());
            System.err.println(e.getMessage());
        }
        return lstObj;
    }

    public Object getDataMapColumnRowField(RowField rowField, HashMap<Integer, ModelMapping> hmMap) {

        Object Obj = null;
        if (rowField.size() != hmMap.size()) {
            System.err.println("Khong du so truong nhu trong bang mapping getDataMapColumnRowField");
        }
        try {
            HashMap<String, Object> hmObject = new HashMap<String, Object>();
            for (int i : hmMap.keySet()) {
                ModelMapping value = hmMap.get(i);
                if (i <= rowField.size()) {
                    Object objValue = null;
                    if (value.getData_type().contains("CHAR")) {
                        objValue = rowField.get(i - 1).getValueAsString().length() > value.getData_lenght()
                                ? rowField.get(i - 1).getValueAsString().substring(0, value.getData_lenght()) : rowField.get(i - 1).getValueAsString();
                    } else {
                        objValue = rowField.get(i - 1).getValueAsString();
                    }
                    hmObject.put(value.getVariable_java(), objValue);
                } else {
                    hmObject.put(value.getVariable_java(), "");
                }

            }
            MappingClassValue mappingvalue = new MappingClassValue("vbsp.ims.model.ModelDsHongheo");
            Obj = mappingvalue.setValueField(hmObject);
        } catch (Exception e) {
            CoreLogger.error(this.getClass().getCanonicalName() + " getDataMapColumnRowField->Exception-> " + e.getMessage());
            System.err.println(e.getMessage());
        }
        return Obj;
    }

    public List<ModelDsHongheo> mappingColumnExcelToTable(DataTable table) {
        List<ModelDsHongheo> lstModel = new ArrayList<>();
        try {

        } catch (Exception e) {
            CoreLogger.error(this.getClass().getCanonicalName() + " mappingColumnExcelToTable->Exception-> " + e.getMessage());
            System.err.println(this.getClass().getCanonicalName() + " mappingColumnExcelToTable->Exception-> " + e.getMessage());
        }

        return lstModel;
    }

    public static void main(String[] args) throws Exception {
        HashMap<Integer, ModelMapping> hmMap = new DaoDsHongheo().getMappingColumn();
        String FileInput = "E:\\VBSP\\4. Source code\\1. Java\\1.IMS_REPORTS_OLD\\Excel_template\\Core-excel\\EXCEL\\Mau nhap So lieu.xls";
        ReaderFileExcel read = new ReaderFileExcel(FileInput);
        read.setStartingRow(3);
//        read.setLastRow(1000);
        read.setLastColumn(12);
        DataTable table = read.readFile();
        new ModelProcessDsHongheo().isCheckData(table);
        Map<Integer, RowField> map = new ModelProcessDsHongheo().getCheckDataError(table);
        List<Object> lst = new ModelProcessDsHongheo().getDataMapColumnTable(table, hmMap);

        System.err.println("size=" + lst.size());
        for (Object obj : lst) {
            ModelDsHongheo value = (ModelDsHongheo) obj;
            System.err.println(value.toString());
        }
        new DaoDsHongheo().saveDsHongheo(lst,"TUNGNV");
    }
}
