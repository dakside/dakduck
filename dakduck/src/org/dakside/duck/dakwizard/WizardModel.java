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

import java.awt.event.ActionListener;

/**
 * Wizard model<br/>
 * Contains steps, available path
 * @author LeTuanAnh <tuananh.ke@gmail.com>
 */
public interface WizardModel {

    /**
     * Move to a specified step
     * @param step
     */
    public void move(WizardStep step);

    /**
     * Get current step
     * @return
     */
    public WizardStep getCurrentStep();

    /**
     * notify listeners
     */
    public void fireStateChanged();

    /**
     * Add action listener to listener list
     * @param listener
     */
    public void addModelListener(ActionListener listener);

    /**
     * Remove action listener from listener list
     * @param listener
     */
    public void removeModelListener(ActionListener listener);

    /**
     * Get current wizard status<br/>
     * @return Should return <br/>
     * WizardDialog.STATUS_ERROR or <br/>
     * WizardDialog.STATUS_FINISHED or <br/>
     * WizardDialog.STATUS_CANCELLED
     */
    public int getStatus();

    /**
     * Set current dialog status
     * @param status must assign the following value: <br/>
     * WizardDialog.STATUS_ERROR or <br/>
     * WizardDialog.STATUS_FINISHED or <br/>
     * WizardDialog.STATUS_CANCELLED
     */
    public void setStatus(int status);
}
