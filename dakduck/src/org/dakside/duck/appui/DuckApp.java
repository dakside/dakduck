/*
 * Copyright (C) 2014 Le Tuan Anh <tuananh.ke@gmail.com>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.dakside.duck.appui;

import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import org.dakside.utils.SystemHelper;

/**
 *
 * @author LeTuanAnh <tuananh.ke@gmail.com>
 */
public abstract class DuckApp {

    private static final Logger logger = Logger.getLogger(DuckApp.class.getName());

    private JFrame myFrame;

    public JFrame getMyFrame() {
        return myFrame;
    }

    protected static void loadLookAndFeel(String lookAndFeelName) {

        System.out.println("Setting up Java GUI LooknFeel");
        System.out.println("--------------------------------------------------");

        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code">
        try {
            UIManager.setLookAndFeel(Class.forName(lookAndFeelName).getName());
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, "Cannot load theme", ex);
        }
        //</editor-fold>

        System.out.println("--------------------------------------------------");
    }

    public DuckApp(JFrame myFrame) {

        this.myFrame = myFrame;
    }

    private void startup() {
        // Execute custom startup
        customStartup();
    }

    /**
     * Start the application
     */
    public void start() {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    startup();
                    myFrame.setVisible(true);
                } catch (Exception ex) {
                    logger.log(Level.SEVERE, "Runtime error. The GUI crashed", ex);
                }
            }
        });
    }

    protected abstract void customStartup();

    //<editor-fold defaultstate="collapsed" desc="Configuration support">
    protected static final String CUSTOM_MODULES_KEY = "CUSMOD";
    protected static final String CUSTOM_MODULES_UI_KEY = "CUSMODUI";
    protected static final String MODULES_UI_KEY_DEF = "config/modulesUI.properties";
    protected static final String MODULES_KEY_DEF = "config/modules.properties";
    private static Properties moduleConfig;
    private static Properties moduleUIConfig;
    private static String modulesKey;
    private static String modulesUIKey;

    protected static synchronized Properties getModuleConfig() {
        if (moduleConfig == null) {
            logger.log(Level.INFO, "Module file location: {0}", getModuleFileName());
            moduleConfig = SystemHelper.getPropertiesFromCustomFile(getModuleFileName());
        }
        return moduleConfig;
    }

    protected static synchronized Properties getModuleUIConfig() {
        if (moduleUIConfig == null) {
            moduleUIConfig = SystemHelper.getPropertiesFromCustomFile(getModuleUIFileName());
        }
        return moduleUIConfig;
    }

    public static synchronized String getModuleFileName() {
        if (modulesKey == null) {
            modulesKey = SystemHelper.getTextFromVMOption(CUSTOM_MODULES_KEY, MODULES_KEY_DEF);
        }
        return modulesKey;
    }

    public static synchronized String getModuleUIFileName() {
        if (modulesUIKey == null) {
            modulesUIKey = SystemHelper.getTextFromVMOption(CUSTOM_MODULES_UI_KEY, MODULES_UI_KEY_DEF);
        }
        return modulesUIKey;
    }

    //</editor-fold>
}
