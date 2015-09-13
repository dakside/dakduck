/*
 *  Copyright (C) 2010 Le Tuan Anh <tuananh.ke@gmail.com>
 * 
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 * 
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 * 
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.dakside.duck.demo.plugins.wizards;

import org.dakside.duck.dakwizard.DefaultWizardModel;
import org.dakside.duck.dakwizard.WizardStep;
import java.util.Arrays;
import java.util.ResourceBundle;

/**
 * A sample wizard model with a linear path
 *
 * @author LeTuanAnh <tuananh.ke@gmail.com>
 */
public class NewUserWizardModel extends DefaultWizardModel {

    private String[] holder = null;

    public NewUserWizardModel() {
        String wizardString = ResourceBundle.getBundle("org.dakside.duck.demo.plugins.wizards.resources.NewUserWizardModel").getString("intro");
        WizardStep[] s = new WizardStep[]{
            new StaticTextStep(this, null, wizardString),
            new InputStep(this, "Name"),
            new InputStep(this, "Address"),
            new InputStep(this, "Birthday"),
            new InputStep(this, "Note")
        };
        initPath(s);
        this.holder = new String[this.linearPath.size()];
    }

    void keyin(String msg) {
        this.holder[currentStep] = msg;
    }

    /**
     * @return the holder
     */
    public String[] getHolder() {
        return Arrays.copyOf(holder, holder.length);
    }

    public String getUserName() {
        return holder[1];
    }

    public String getAddress() {
        return holder[2];
    }

    public String getBirthday() {
        return holder[3];
    }

    public String getNote() {
        return holder[4];
    }
}
