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

import javax.swing.JPanel;

/**
 * An abstract panel wizard step for DefaultWizardModel
 * @author LeTuanAnh <tuananh.ke@gmail.com>
 * @param <T>
 */
public abstract class AbstractWizardStep<T extends DefaultWizardModel> extends JPanel implements WizardStep {

    protected T wizardModel = null;
    protected boolean needFocus = true;

    /**
     * AbstractWizardStep constructor
     * @param wizardModel
     */
    public AbstractWizardStep(T wizardModel) {
        setWizardModel(wizardModel);
    }

    /**
     * AbstractWizardStep constructor
     * @param wizardModel
     * @param needFocus is this step require focus?
     */
    public AbstractWizardStep(T wizardModel, boolean needFocus) {
        setWizardModel(wizardModel);
        this.needFocus = needFocus;
    }

    public WizardStep getNextStep() {
        return getWizardModel().getNextStep();
    }

    public WizardStep getPreviousStep() {
        return getWizardModel().getPreviousStep();
    }

    public abstract boolean isCancellable();

    public abstract boolean isFinishable();

    public abstract boolean canGoNext();

    public abstract boolean canGoBack();

    /**
     * A virtual function, should override to do something when this step is invoked
     */
    public void onShow() {
        //do nothing
    }

    /**
     * By default, all steps require focus when shown.<br/>
     * @return
     */
    public boolean requireFocus() {
        return needFocus;
    }

    public Object getView() {
        return this;
    }

    public boolean isAllowed(int action) {
        switch (action) {
            case WizardDialog.NEXT:
                return getNextStep() != null && canGoNext();
            case WizardDialog.BACK:
                return getPreviousStep() != null && canGoBack();
            case WizardDialog.CANCEL:
                return isCancellable();
            case WizardDialog.FINISH:
                return isFinishable();
            default:
                return false;
        }
    }

    @Override
    public void doAction(int action) {
        switch (action) {
            case WizardDialog.NEXT:
                getWizardModel().move(getNextStep());
                return;
            case WizardDialog.BACK:
                getWizardModel().move(getPreviousStep());
                return;
            case WizardDialog.FINISH:
                finish();
                getWizardModel().setStatus(WizardDialog.STATUS_FINISHED);
                return;
            case WizardDialog.CANCEL:
                cancel();
                getWizardModel().setStatus(WizardDialog.STATUS_CANCELLED);
                return;
            default:
                //do nothing
                break;
        }
    }

    /**
     * @return the wizardModel
     */
    public T getWizardModel() {
        return wizardModel;
    }

    /**
     * @param wizardModel the wizardModel to set
     */
    public void setWizardModel(T wizardModel) {
        this.wizardModel = wizardModel;
    }

    /**
     * A virtual function, should override to do something when user complete the wizard
     */
    protected void finish() {
    }

    /**
     * A virtual function, should override to do something when user cancel the wizard
     */
    private void cancel() {
    }
}
