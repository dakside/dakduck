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
package org.dakside.duck.demo;

import org.dakside.duck.appui.DuckApp;
import org.dakside.duck.plugins.AppCentral;
import org.dakside.duck.plugins.FunctionPool;

/**
 *
 * @author LeTuanAnh <tuananh.ke@gmail.com>
 */
public class DuckDemo extends DuckApp {

    private static final String LOOK_AND_FEEL_NAME = "de.muntjak.tinylookandfeel.TinyLookAndFeel";

    private final MainFrame mainFrame;

    public DuckDemo(MainFrame mainFrame) {
        super(mainFrame);
        this.mainFrame = mainFrame;
    }

    public static void main(String[] args) {
        // Try to run the app with this:
        //java -DCUSMOD=cm.prop -DCUSMODUI=cmui.prop -jar org.dakside.duck.demo.jar

        try {
            String modulesText = getModuleConfig().getProperty("modules");
            String txtDemo = getModuleUIConfig().getProperty("txt_Demo");
            System.out.printf("modules=%s\n", modulesText.replace(" ", " --- "));
            System.out.printf("txt_Demo=%s\n", txtDemo);
            System.out.printf("ModuleUIFile=%s\n", getModuleUIFileName());
        } catch (Exception ex) {
        }  
        mainGUI(args);
    }

    private static String[] getCustomModules() {
        return getModuleConfig().getProperty("modules").split(" ");
    }

    public static void mainGUI(String[] args) {
        DuckApp.loadLookAndFeel(LOOK_AND_FEEL_NAME);
        //FunctionPool.getInstance().scan();
        FunctionPool.getInstance().scan(getCustomModules());
        DuckDemo demo = new DuckDemo(new MainFrame());
        demo.mainFrame.getStartPage().getGroupModulePanel().updateResourceCentre(getModuleUIFileName());
        demo.mainFrame.getStartPage().getGroupModulePanel().load();
        // Indicate which is the main form
        AppCentral.initApp(demo.mainFrame);
        demo.start();
    }

    @Override
    protected void customStartup() {
    }
}
