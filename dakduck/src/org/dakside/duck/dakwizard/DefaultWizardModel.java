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

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import javax.swing.event.EventListenerList;

/**
 * A linear wizard model
 * @author LeTuanAnh <tuananh.ke@gmail.com>
 */
public class DefaultWizardModel implements WizardModel {

    protected ArrayList<WizardStep> linearPath;
    protected int currentStep;
    private final EventListenerList listenerList = new EventListenerList();
    private ActionEvent evt = null;
    private int status = 0;

    public DefaultWizardModel() {
    }

    public DefaultWizardModel(WizardStep[] steps) {
        initPath(steps);
    }

    public void move(WizardStep step) {
        this.currentStep = linearPath.indexOf(step);
        fireStateChanged();
    }

    public WizardStep getCurrentStep() {
        return linearPath.get(currentStep);
    }

    public WizardStep getNextStep() {
        return (currentStep + 1 < linearPath.size())
                ? linearPath.get(currentStep + 1)
                : null;
    }

    public WizardStep getPreviousStep() {
        return (currentStep > 0)
                ? linearPath.get(currentStep - 1)
                : null;
    }

    public void fireStateChanged() {
        Object[] listeners = listenerList.getListenerList();
        for (int i = listeners.length - 2; i >= 0; i -= 2) {
            if (listeners[i] == ActionListener.class) {
                if (evt == null) {
                    evt = new ActionEvent(this, WizardDialog.STEP_ACTION, "");
                }
                ((ActionListener) listeners[i + 1]).actionPerformed(evt);
            }
        }
    }

    public void addModelListener(ActionListener l) {
        listenerList.add(ActionListener.class, l);
    }

    public void removeModelListener(ActionListener l) {
        listenerList.remove(ActionListener.class, l);
    }

    /**
     * init a new linear path
     * @param steps
     */
    protected void initPath(WizardStep[] steps) {
        if (steps == null || steps.length == 0) {
            return;
        }

        this.linearPath = new ArrayList<WizardStep>();
        Collections.addAll(linearPath, steps);
        currentStep = 0;
    }

    /**
     * @return the status
     */
    public int getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(int status) {
        if (WizardDialog.isStatus(status)) {
            this.status = status;
        } else {
            throw new RuntimeException("Invalid status");
        }
    }

    /**
     * is wizard finished<br/>
     * A wrapper for getStatus() == WizardDialog.STATUS_FINISHED
     * @return
     */
    public boolean isFinished() {
        return getStatus() == WizardDialog.STATUS_FINISHED;
    }

    /**
     * is wizard cancelled<br/>
     * A wrapper for getStatus() == WizardDialog.STATUS_CANCELLED
     * @return
     */
    public boolean isCancelled() {
        return getStatus() == WizardDialog.STATUS_CANCELLED;
    }

    /**
     * is error was raised during the wizard<br/>
     * A wrapper for getStatus() == WizardDialog.STATUS_ERROR
     * @return
     */
    public boolean isError() {
        return getStatus() == WizardDialog.STATUS_ERROR;
    }
}
