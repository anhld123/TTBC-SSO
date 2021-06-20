/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package vbsp.ims.dtw;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Trung
 */
public class DirectoryLibrary {
    
  public static List<File> listFilesForFolder(String directory_path) {
      ArrayList<File> files = new ArrayList<>();
      File folder = new File(directory_path);
        // Create a FileFilter 
            FileFilter filter = new FileFilter() { 
                @Override
                public boolean accept(File f) 
                { 
                    return f.getName().toLowerCase().endsWith("xls")||f.getName().toLowerCase().endsWith("xlsx"); 
                } 
            }; 
      
        File[] listOfFiles = folder.listFiles(filter);
        for (File file : listOfFiles) {
            if (file.isFile()) {
                files.add(file);
            }
        }
        return files;
    }
}
