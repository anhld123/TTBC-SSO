/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vbsp.ims.chtrinh_cn;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class StartupListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        try {

            System.out.println("Preloading ActionChtrinhcnMain...");

            // Ép class load
            ActionChtrinhcnMain.getStructDesc();
            ActionChtrinhcnMain.getArrayDesc();

            System.out.println("Preload completed.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
    }
}
