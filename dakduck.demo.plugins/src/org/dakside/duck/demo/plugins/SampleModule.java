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
package org.dakside.duck.demo.plugins;

import org.dakside.duck.demo.plugins.demolog.democonfig.SampleConfigView;
import org.dakside.duck.demo.plugins.demolog.SampleLogView;
import java.awt.Component;
import org.dakside.duck.dakwizard.WizardDialog;
import org.dakside.duck.demo.plugins.wizards.NewUserWizardModel;
import org.dakside.duck.plugins.AppCentral;
import org.dakside.duck.plugins.Function;
import org.dakside.duck.plugins.Unloadable;

/**
 *
 * @author LeTuanAnh <tuananh.ke@gmail.com>
 */
public class SampleModule implements Unloadable {

    private Component sampleConfigView = null;
    private SampleLogView sampleLogView = null;

    public SampleLogView getLogViewForm() {
        if (sampleLogView == null) {
            sampleLogView = new SampleLogView();
        }
        return sampleLogView;
    }

    @Override
    public void unload() {
        sampleConfigView = null;
        sampleLogView = null;
    }

    /**
     * This create a menu item which shows a config form when clicked.
     *
     * @return
     */
    @Function(Text = "DemoPluginConfigView", Description = "DemoPluginConfigViewDesc",
            IconPath = "icon_demo_plugin", Category = "Demo", Location = Function.MENU)
    public synchronized Component showConfigForm() {
        if (sampleConfigView == null) {
            sampleConfigView = new SampleConfigView();
        }
        return sampleConfigView;
    }

    /**
     * This creates a button in a specific feature group in Start Page which
     * shows the log form when clicked
     *
     * @return the form to be shown as a component
     */
    @Function(Text = "SampleLogView", Description = "SampleLogViewDesc",
            IconPath = "icon_demo_plugin", Category = "Demo", Location = Function.STARTPAGE)
    public synchronized Component showLogForm() {
        return getLogViewForm();
    }

    /**
     * This creates a button on the toolbar of the main app which shows a
     * message box when clicked. As demonstrated, the plugin feature doesn't
     * necessarily return a component. In this case, it returns null.
     *
     * @return
     */
    @Function(Text = "SampleAbout", Description = "SampleAboutDesc",
            IconPath = "icon_demo_plugin", Category = "Demo", Location = Function.TOOLBAR)
    public synchronized Component showAboutBox() {
        AppCentral.getAPIDelegate().popup("I'm a sample plugin, more or less ...");
        return null;
    }

    /**
     * This creates another button on the toolbar. When clicked it will first
     * show a wizard dialog with several steps to user for data input. After
     * that it will activate the log form to display results.
     *
     * @return
     */
    @Function(Text = "btnNewUser", Description = "btnNewUserDesc",
            IconPath = "icon_demo_plugin", Category = "Demo", Location = Function.TOOLBAR)
    public synchronized Component createNewUser() {
        StringBuilder txtResults = new StringBuilder();
        try {
            NewUserWizardModel m = (NewUserWizardModel) WizardDialog.showDialog(new NewUserWizardModel(), "Create user wizard", "Actions");
            if (m.isFinished()) {
                txtResults.append("User information:\r\n");
                txtResults.append("    + Username: ").append(m.getUserName()).append("\r\n");
                txtResults.append("    + Birthday: ").append(m.getBirthday()).append("\r\n");
                txtResults.append("    + Address : ").append(m.getAddress()).append("\r\n");
                txtResults.append("    + Note    : ").append(m.getNote()).append("\r\n");
            } else if (m.isCancelled()) {
                txtResults.append("You cancelled the wizard!");
            }

        } catch (Exception ex) {
            txtResults.append("Error was raised");
        }

        getLogViewForm().logMessage(txtResults.toString());
        return getLogViewForm();
    }

}
