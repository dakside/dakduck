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
import org.dakside.duck.plugins.AppCentral;
import org.dakside.duck.plugins.Function;
import org.dakside.duck.plugins.Unloadable;

/**
 *
 * @author LeTuanAnh <tuananh.ke@gmail.com>
 */
public class SampleModule implements Unloadable {

    private static Component sampleConfigView = null;
    private static Component sampleLogView = null;

    @Override
    public void unload() {
        sampleConfigView = null;
        sampleLogView = null;
    }

    @Function(Text = "DemoPluginConfigView", Description = "DemoPluginConfigViewDesc",
            IconPath = "icon_demo_plugin", Category = "Demo", Location = Function.MENU)
    public synchronized Component showConfig() {
        if (sampleConfigView == null) {
            sampleConfigView = new SampleConfigView();
        }
        return sampleConfigView;
    }

    @Function(Text = "SampleLogView", Description = "SampleLogViewDesc",
            IconPath = "icon_demo_plugin", Category = "Demo", Location = Function.STARTPAGE)
    public synchronized Component showLangViewStartPage() {
        if (sampleLogView == null) {
            sampleLogView = new SampleLogView();
        }
        return sampleLogView;
    }

    @Function(Text = "SampleAbout", Description = "SampleAboutDesc",
            IconPath = "icon_demo_plugin", Category = "Demo", Location = Function.TOOLBAR)
    public synchronized Component showLangViewAbout() {
        AppCentral.getAPIDelegate().popup("I'm a sample plugin, more or less ...");
        return null;
    }

}
