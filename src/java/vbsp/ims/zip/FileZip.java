/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vbsp.ims.zip;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;
import vbsp.ims.fileutil.FileUtil;

/**
 *
 * @author LION
 */
public class FileZip {

    static final int BUFFER = 2048;

//    public static void main(String[] a) throws Exception {
//        ArrayList<String> ArrLstFile = new ArrayList<>();
//        ArrLstFile.add("D:\\web\\AJAXDEMO\\build\\web\\HSTDCT_002501_30042014.001");
//        ArrLstFile.add("D:\\web\\AJAXDEMO\\build\\web\\HSTDCT_002502_30042014.001");
//        ArrLstFile.add("D:\\web\\AJAXDEMO\\build\\web\\HSTDCT_002503_30042014.001");
//        ArrLstFile.add("D:\\web\\AJAXDEMO\\build\\web\\HSTDCT_002504_30042014.001");
//
//        ArrLstFile.add("D:\\web\\AJAXDEMO\\build\\web\\HSTDCT_002505_30042014.001");
//        ArrLstFile.add("D:\\web\\AJAXDEMO\\build\\web\\HSTDCT_002506_30042014.001");
//        ArrLstFile.add("D:\\web\\AJAXDEMO\\build\\web\\HSTDCT_002507_30042014.001");
//        ArrLstFile.add("D:\\web\\AJAXDEMO\\build\\web\\HSTDCT_002508_30042014.001");
//
//        System.err.println(new Date());
//        int random = (int) (Math.random() * 50000 + 1);
//
//        System.err.println(random);
//
//        FileZip.UnzipFile("H:\\Picture\\IMG_1383.zip", "H:\\Picture");
//    }

    static public boolean ZipFileFromArray(ArrayList<String> ArrLstFile, String strdestZipFile)
            throws Exception {
        boolean bSuccess = false;
        if (ArrLstFile.isEmpty() || ArrLstFile.isEmpty()) {
            return bSuccess;
        }
        //Khoi tao de ghi file
        ZipOutputStream zip;
        FileOutputStream fileWriter;

        fileWriter = new FileOutputStream(strdestZipFile);
        zip = new ZipOutputStream(fileWriter);

        for (String strFullPathFile : ArrLstFile) {
            //Kiem tra file neu co file thi nen ko co file thi ko nen nua?
            File fileOut = new File(strFullPathFile);
            if (!fileOut.exists()) {
                System.err.println("Khong co file du lieu " + strFullPathFile);
                //setMessage("Lỗi tìm thấy file dữ liệu đã xuất ra");
                continue;
            }
            String strFileName = fileOut.getName();
            byte[] buf = new byte[1024];
            int len;
            FileInputStream in = new FileInputStream(strFullPathFile);
            zip.putNextEntry(new ZipEntry(strFileName));
            while ((len = in.read(buf)) > 0) {
                zip.write(buf, 0, len);
            }
        }
        zip.flush();
        zip.close();
        return bSuccess;
    }

    public static boolean UnzipFile(String zip_file, String output_folder) {
        ZipInputStream zis = null;
        try {
            BufferedOutputStream dest;
            zis = new ZipInputStream(
                    new BufferedInputStream(
                            new FileInputStream(zip_file)));
            
            ZipEntry entry;
                                                                        
            // Giai nen 
            while ((entry = zis.getNextEntry()) != null) {
                System.out.println("Extracting: " + entry.getName());
                int count;
                byte data[] = new byte[BUFFER];

                if (entry.isDirectory()) {
                    new File(output_folder + "/" + entry.getName()).mkdirs();
                    continue;
                } else {
                    int di = entry.getName().lastIndexOf('/');
                    if (di != -1) {
                        new File(output_folder + "/" + entry.getName()
                                .substring(0, di)).mkdirs();
                    }
                }
                FileOutputStream fos = new FileOutputStream(output_folder + "/"
                        + entry.getName());
                dest = new BufferedOutputStream(fos);
                while ((count = zis.read(data)) != -1) {
                    dest.write(data, 0, count);
                }
                dest.flush();
                dest.close();
            }
            return true;
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FileZip.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } catch (IOException ex) {
            Logger.getLogger(FileZip.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            try {
                zis.close();
            } catch (IOException ex) {
                Logger.getLogger(FileZip.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
