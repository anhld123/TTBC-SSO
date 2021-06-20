/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vbsp.ims.fileutil;

import java.io.File;

/**
 *
 * @author Trung Nguyen
 */
public class FileUtil {

    public static void deleteFolder(String path) {

        File directory = new File(path);
        //make sure directory exists
        if (!directory.exists()) {
            System.out.println("Directory does not exist.");
            //System.exit(0);
        } else {
            delete(directory);
        }

    }

    private static void delete(File file) {
        if (file.isDirectory()) {

            //directory is empty, then delete it
            if (file.list().length == 0) {

                file.delete();
                System.out.println("Directory is deleted : "
                        + file.getAbsolutePath());

            } else {

                //list all the directory contents
                String files[] = file.list();

                for (String temp : files) {
                    //construct the file structure
                    File fileDelete = new File(file, temp);

                    //recursive delete
                    delete(fileDelete);
                }

                //check the directory again, if empty then delete it
                if (file.list().length == 0) {
                    file.delete();
                    System.out.println("Directory is deleted : "
                            + file.getAbsolutePath());
                }
            }

        } else {
            //if file, then delete it
            file.delete();
            System.out.println("File is deleted : " + file.getAbsolutePath());
        }

    }
}
