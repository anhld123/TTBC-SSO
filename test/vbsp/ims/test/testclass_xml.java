/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vbsp.ims.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Random;
import vbsp.ims.xml.ImsReadWriteXmlFile;

/**
 *
 * @author lion
 */
public class testclass_xml {
    public static void main(String[] args) 
    {
        HashMap<String, String> hmData = new HashMap<String, String>();
        
          Random rand = new Random();
        for (int i=1; i<20;i++)
        {
            String sKey="KEY_"+Integer.toString(i);
            String data="";
            for (int j = 1; j<20;j++)
                data+="#DATA_"+Integer.toString(rand.nextInt(i*10 + 1));
            
            hmData.put(sKey, data);
        }
        
//        String strData="#DATA_30#DATA_65#DATA_3#DATA_57#DATA_13#DATA_71#DATA_95#DATA_86#DATA_59#DATA_82#DATA_74#DATA_5#DATA_95#DATA_66#DATA_48#DATA_88#DATA_31#DATA_85#DATA_81#";
//         ArrayList<String> ArrlstPosCd = new ArrayList<String>(Arrays.asList(strData.split("#")));
//         System.err.println(strData.substring(1));
//          for (int i = 0; i < ArrlstPosCd.size(); i++) {
//                String strPosCd = ArrlstPosCd.get(i);
//                System.err.println(strPosCd);
//          }
        String sReportDate="15-oct-2014";
         System.err.println(sReportDate.substring(sReportDate.length()-4));
        ImsReadWriteXmlFile readxml = new ImsReadWriteXmlFile();
        HashMap<String, String> hmDataBcnt=readxml.readFileXML("D:\\31340109_GIAO_KHTD.xml");
//                readxml.createFileXMLBCNT("01","BCNTABC_TEST","08-oct-2014","ADMIN", "003401", "2",
//                "Y",  hmData, "", "D:\\tungnv1.xml");
        for(String key:hmDataBcnt.keySet())
            System.err.println(key+"  --->  "+hmDataBcnt.get(key));
//        readxml.createFileXMLBCNT("01","BCNTABC_TEST","08-oct-2014","ADMIN", "003401", "2",
//                "Y",  hmData, "3-2014", "D:\\tungnv1.xml");
//        createFileXMLBCNT(String sReportType, String sReportId, String sReportDate, String sUserId, String sPosCd, String sGrade,
//            String sFlagData, HashMap<String, String> hmDataReport, String sQuyBC, String sFileName)
    }
}
