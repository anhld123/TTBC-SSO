package vbsp.ims.jasper;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;

import net.sf.jasperreports.components.list.ListComponent;
import net.sf.jasperreports.engine.JRBand;
import net.sf.jasperreports.engine.JRChild;
import net.sf.jasperreports.engine.JRElement;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRImage;
import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.JRSubreport;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JRDesignComponentElement;
import net.sf.jasperreports.engine.design.JRDesignExpression;
import net.sf.jasperreports.engine.design.JRDesignImage;
import net.sf.jasperreports.engine.design.JRDesignSubreport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.util.JRProperties;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
//import static sun.security.krb5.Confounder.bytes;

public class BuilderJasper {

    public String sFileNameJas = null;
    private JasperDesign jsrDesign = null;
    private JasperReport jsrReport = null;

    public BuilderJasper(String fileName) throws JRException {
        JRProperties.setProperty(JRProperties.QUERY_EXECUTER_FACTORY_PREFIX + "plsql",
                "com.jaspersoft.jrx.query.PlSqlQueryExecuterFactory");
        jsrDesign = JRXmlLoader.load(fileName);        
    }

    public BuilderJasper(JasperDesign jsrDesign) {
        this.jsrDesign = jsrDesign;
    }

    public BuilderJasper(JasperReport jsrReport) {
        this.jsrReport = jsrReport;
        //jsrDesign.
    }

    public void ReadFileSub(String sFileName) {
        File srcFile = new File(sFileName);
        if (!srcFile.exists()) {
            System.err.println("Khong tim thay file");
            return;
        }
        JasperDesign jsrDesign;
        try {
            jsrDesign = JRXmlLoader.load(srcFile);
            JRElement[] element = jsrDesign.getTitle().getElements();
            for (int i = 0; i < element.length; i++) {
                if (element[i] instanceof JRSubreport) {
                    JRSubreport subreport = (JRSubreport) element[i];
                    String sSubName = subreport.getExpression().getText();
                    System.out.println(sSubName);

                }
            }
        } catch (JRException ex) {
            Logger.getLogger(BuilderJasper.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //Ham nay lay ra mang cua nhung phan tu chua subreport
    private ArrayList<JRSubreport> getSubreport(JRBand band) {

        ArrayList<JRSubreport> ArrNameSub = new ArrayList<>();
        if (band == null) {
            return ArrNameSub;
        }
        try {
            JRElement[] element = band.getElements();

            for (int i = 0; i < element.length; i++) {
                if (element[i] instanceof JRSubreport) {
                    JRSubreport subreport = (JRSubreport) element[i];
                    //String sSubName = subreport.getExpression().getText();
                    ArrNameSub.add(subreport);
                }
            }
        } catch (Exception e) {
        }
        return ArrNameSub;
    }

    private ArrayList<JRImage> getImageBand(JRBand band) {

        ArrayList<JRImage> ArrNameSub = new ArrayList<>();
        if (band == null) {
            return ArrNameSub;
        }
        try {
            JRElement[] element = band.getElements();

            for (int i = 0; i < element.length; i++) {
                if (element[i] instanceof JRImage) {
                    JRImage image = (JRImage) element[i];
                    //String sSubName = subreport.getExpression().getText();
                    ArrNameSub.add(image);
                }
            }
        } catch (Exception e) {
        }
        return ArrNameSub;
    }

    public ArrayList<JRImage> getImageBand() {
        ArrayList<JRImage> ArrImgName = new ArrayList<JRImage>();
        JRBand[] bands = jsrDesign.getAllBands();
        try {
            for (final JRBand band : bands) {
                ArrImgName.addAll(getImageBand(band));
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return ArrImgName;
    }

    //Lay ra mang 1 chuoi chua cac duong dan anh
    private ArrayList<String> getImage(JRBand band) {
        ArrayList<String> ArrImgName = new ArrayList<>();
        if (band == null) {
            return ArrImgName;
        }
        try {
            JRElement[] element = band.getElements();
            for (JRElement element1 : element) {
                if (element1 instanceof JRImage) {
                    JRImage subreport = (JRImage) element1;
                    String sSubName = subreport.getExpression().getText();
                    ArrImgName.add(sSubName);
                }
            }
        } catch (Exception e) {
        }
        return ArrImgName;
    }

    public ArrayList<String> getImage() {
        ArrayList<String> ArrImgName = new ArrayList<>();
        JRBand[] bands = jsrDesign.getAllBands();
        try {
            for (final JRBand band : bands) {
                ArrImgName.addAll(getImage(band));
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return ArrImgName;
    }

    public ArrayList<String> getSubreport() {
        ArrayList<String> ArrNameSub = new ArrayList<>();
        ArrayList<JRSubreport> ArrSubReport = new ArrayList<>();
        JRBand[] bands = jsrDesign.getAllBands();
        try {
            for (final JRBand band : bands) {
                ArrSubReport.addAll(getSubreport(band));
            }
            Iterator<JRSubreport> it = ArrSubReport.iterator();
            while (it.hasNext()) {
                JRSubreport subreport = (JRSubreport) it.next();
                String sSubName = subreport.getExpression().getText();
                ArrNameSub.add(sSubName);
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return ArrNameSub;
    }

    public ArrayList<JRBand> getBand() {
        ArrayList<String> ArrNameSub = new ArrayList<>();
        ArrayList<JRBand> ArrBand = new ArrayList<>();
        JRBand[] bands = jsrDesign.getAllBands();
        try {
            for (final JRBand band : bands) {
                ArrBand.add(band);
            }

        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return ArrBand;
    }

    //Lay ra 1 mang tham so
    public ArrayList<JasperParam> getParameters() {
        ArrayList<JasperParam> ArrPara = new ArrayList();
        try {
            JRParameter[] para = jsrDesign.getParameters();
            int order = 1;
            for (JRParameter param : para) {
                if (!param.isSystemDefined() && param.isForPrompting()) {
                    String sParaName = param.getName();
                    String sParaType = param.getValueClassName();
                    ArrPara.add(new JasperParam(sParaName, sParaType, "No descript", order));
                    order++;
                }
            }
        } catch (Exception e) {
            System.err.println("getParameters error~"+e.getMessage());
        }
        return ArrPara;
    }

    public static void setImageFilename(ArrayList<JRImage> ArrImage, String newValue) {
        Iterator<JRImage> it = ArrImage.iterator();
        while (it.hasNext()) {
            JRImage jrImage = (JRImage) it.next();
            String sSubName = jrImage.getExpression().getText();
            JRDesignExpression newImageExpr = new JRDesignExpression();
            newImageExpr.setText(newValue);
            newImageExpr.setValueClassName(jrImage.getExpression().getValueClassName());
            ((JRDesignImage) jrImage).setExpression(newImageExpr);
        }
    }

    private void changeSubReportPath(JasperDesign jasperDesign) {

        Object[] objExpressions = jasperDesign.getExpressions().toArray();

        for (Object objExpression : objExpressions) {
            JRDesignExpression jrExpression = (JRDesignExpression) objExpression;
            String expressionText = jrExpression.getText();
            String sPathfull;
            if ((expressionText.contains(".class")) || (expressionText.contains(".jasper"))) {
                jrExpression.setText("\"" + System.getProperty("report.child.basepath") + expressionText.substring(1));
                sPathfull = expressionText.substring(1);
                System.out.println(sPathfull);
            }
        }
    }

    private String escapeWinSeparators(String path) {
        final StringBuffer newpath = new StringBuffer();
        final char sep = '\\';
        if (File.separator.toCharArray()[0] == sep) {
            final char[] ca = path.toCharArray();
            char ct;
            for (int i = 0; i < ca.length; i++) {
                ct = ca[i];
                if (ct != sep) {
                    newpath.append(ct);
                } else {
                    newpath.append(sep);
                    newpath.append(ct);
                }
            }
            path = newpath.toString();
        }
        return path;
    }

    protected void updateSubreportsPath(JasperDesign Design, String sPath) {
        final List<JRBand> bands = getBand();

        for (final JRBand band : bands) {
            if (band != null) {
                final List<JRChild> f = band.getChildren();
                final Iterator<JRChild> it = f.iterator();

                Object obj;
                while (it.hasNext()) {
                    obj = it.next();
                    if (obj instanceof JRDesignSubreport) {
                        final JRDesignSubreport subreport = (JRDesignSubreport) obj;
                        final JRDesignExpression varExp = (JRDesignExpression) subreport.getExpression();
                        String sFile = NormalPath(varExp.getText().substring(1, varExp.getText().length() - 1));
                        File file = new File(sFile);
                        String sFileName = file.getName();
                        String path = new File(sPath, sFileName).getAbsolutePath();
                        //String path = "\"" + sPath
                        //		+ varExp.getText().substring(1, varExp.getText().length() - 1) + "\"";
                        path = "\"" + path + "\"";
                        path = escapeWinSeparators(path);
                        //path=path.replaceAll("\\\\", "\\");
                        varExp.setText(path);
                    }
                }
            }
        }
    }

    //Ham nay update duong dan file anh
    protected void updateImagesPath(JasperDesign Design, String sPath) {
        Object obj = null;
        final JRBand title = Design.getTitle();
        if (title == null) {
            return;
        }
        final List<JRChild> f = title.getChildren();
        final Iterator<JRChild> it = f.iterator();

        while (it.hasNext()) {
            obj = it.next();
            //Neu tren title duoc dua vao List 
            if (obj instanceof JRDesignComponentElement) {
                final JRDesignComponentElement list = (JRDesignComponentElement) obj;
                final ListComponent compon = (ListComponent) list.getComponent();
                final JRElement[] element = compon.getContents().getElements();
                for (JRElement element1 : element) {
                    if (element1 instanceof JRDesignImage) {
                        final JRDesignImage img = (JRDesignImage) element1;
                        final JRDesignExpression varExp = (JRDesignExpression) img.getExpression();
                        //String sFile =varExp.getText();
                        //System.err.println(sFile);
                        String path = "\"" + sPath
                                + NormalPath(varExp.getText().substring(1, varExp.getText().length() - 1)) + "\"";
                        //System.out.println(path);
                        path = escapeWinSeparators(path);
                        //System.out.println(path);
                        varExp.setText(path);
                    }
                }
                //System.err.println("Day la list");
            }
            //Neu la anh
            if (obj instanceof JRDesignImage) {
                final JRDesignImage img = (JRDesignImage) obj;
                final JRDesignExpression varExp = (JRDesignExpression) img.getExpression();
                String sFile = varExp.getText();
                if (isCheckParameters(sFile)) {
                    continue;
                }
                //System.err.println(sFile);
                String path = "\"" + sPath
                        + NormalPath(varExp.getText().substring(1, varExp.getText().length() - 1)) + "\"";
                //System.out.println(path);
                path = escapeWinSeparators(path);
                //System.out.println(path);
                varExp.setText(path);
            }
        }
    }

    public boolean isCheckParameters(String sInput) {
        //$P{LOGO}, $P{LOGO}+"image.png"
        //Co cong them tham so dang sau chang han nhu "image.png"
        if (sInput.indexOf('+') > 0) {
            return false;
        } else //Truong hop ko co tham so dang sau
        {	//Neu ma tim thay la tham so
            if (sInput.contains("$P{")) {
                return true;
            }
        }
        return false;
    }

    public String NormalPath(String sPath) {
        String sNorPath = null;
        //Cat luon ky tu dau tien neu la ky tu / hoac \
        if (sPath.charAt(0) == '"') {
            final char ch = sPath.charAt(1);
            if (ch == '\\' || ch == '/') {
                sNorPath = "\"" + sPath.substring(2, sPath.length() - 1) + "\"";
            }
        } else {
            final char ch = sPath.charAt(0);
            if (ch == '\\' || ch == '/') {
                sNorPath = sPath.substring(1);
            }
        }
        //Neu co cong voi tham so thi cat di
        if (sPath.lastIndexOf('+') > 0) {
            int len = sPath.lastIndexOf('+');
            sNorPath = sPath.substring(len + 1, sPath.length());
        }
        if (sNorPath == null) {
            sNorPath = sPath;
        }
        return sNorPath.replace('\"', ' ').trim();
    }

    public boolean WriteFileReportEOD(String sFileName, byte[] bytes) {
        //boolean bSuc = true;
        FileOutputStream stream = null;
        try {
            stream = new FileOutputStream(sFileName);
            try {
                stream.write(bytes);
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return false;
        } finally {
            try {
                stream.close();
                return true;
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                return false;
            }
        }
    }

    public HashMap CompareHash(HashMap<String, String> Hashinput, ArrayList<String> getEodReport) {
        HashMap OutHash = new HashMap();
        Iterator<String> hash = Hashinput.keySet().iterator();

        while (hash.hasNext()) {
            //Lay ra tham so va gia tri duoc dua vao tu chuong trinh
            String key = hash.next();
            String value = (String) Hashinput.get(key);
            Iterator<String> it = getEodReport.iterator();
            while (it.hasNext()) {
                //Tham so lay ra trong bao cao
                String sPara = it.next();
                //Neu tham so truyen vao trung voi tham so cua bao cao
                if (sPara.equals(key)) {
                    //Dua vao 1 mang hashmap
                    OutHash.put(sPara, value);
                }
            }
            //System.out.println(key+" "+value);
        }
        return OutHash;
    }

    public static void main(String[] args) {
        System.out.println(Locale.GERMANY.toString());
    }
}
