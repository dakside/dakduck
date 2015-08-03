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
package examples.littlewizard;

import org.dakside.duck.dakwizard.DefaultWizardModel;
import org.dakside.duck.dakwizard.WizardStep;
import java.util.Arrays;
import java.util.ResourceBundle;

/**
 * A sample wizard model with a linear path
 * @author LeTuanAnh <tuananh.ke@gmail.com>
 */
public class MyWizardModel extends DefaultWizardModel {

    private String[] holder = null;

    public MyWizardModel() {
        String wizardString = ResourceBundle.getBundle("examples.littlewizard.resources.MyWizardModel").getString("intro");
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
}
