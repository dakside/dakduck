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
package org.dakside.duck.dakwizard;

/**
 * Wizard step interface
 * @author LeTuanAnh <tuananh.ke@gmail.com>
 */
public interface WizardStep {

    /**
     * Get next step
     * @return null if not available
     */
    public WizardStep getNextStep();

    /**
     * Get previous step
     * @return null if not available
     */
    public WizardStep getPreviousStep();

    /**
     * This step can be cancel or not
     * @return
     */
    public boolean isCancellable();

    /**
     * Can the user stop the wizard here?
     * @return
     */
    public boolean isFinishable();

    /**
     * Get wizard view
     * @return
     */
    public Object getView();

    /**
     * Check if an action is allowed or not<br/>
     * Action can be WizardDialog.FINISH<br/>
     * Action can be WizardDialog.CANCEL<br/>
     * Action can be WizardDialog.BACK<br/>
     * Action can be WizardDialog.NEXT<br/>
     * @param action
     * @return
     */
    public boolean isAllowed(int action);

    /**
     * Check if an action is allowed or not<br/>
     * Action can be WizardDialog.FINISH<br/>
     * Action can be WizardDialog.CANCEL<br/>
     * Action can be WizardDialog.BACK<br/>
     * Action can be WizardDialog.NEXT
     * @param action
     * @return
     */
    public void doAction(int action);

    /**
     * Will be called when shown to dialog
     */
    public void onShow();

    /**
     * This step require focus when show
     * @return
     */
    public boolean requireFocus();
}
