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

/*
 * InputStep.java
 *
 * Created on Jan 24, 2010, 1:22:04 AM
 */
package org.dakside.duck.demo.plugins.wizards;

import org.dakside.duck.dakwizard.AbstractWizardStep;
import org.dakside.duck.dakwizard.WizardDialog;
import java.awt.event.KeyEvent;

/**
 * A sample input step
 *
 * @author LeTuanAnh <tuananh.ke@gmail.com>
 */
public class InputStep extends AbstractWizardStep<NewUserWizardModel> {

    /**
     * Creates new form InputStep
     */
    public InputStep(NewUserWizardModel model, String text) {
        super(model);
        initComponents();
        jLabel1.setText(text);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txtUserInput = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();

        addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                formFocusGained(evt);
            }
        });

        txtUserInput.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtUserInputKeyReleased(evt);
            }
        });

        jLabel1.setText("Info");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtUserInput, javax.swing.GroupLayout.DEFAULT_SIZE, 288, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtUserInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void txtUserInputKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtUserInputKeyReleased
        wizardModel.keyin(txtUserInput.getText());
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (isAllowed(WizardDialog.NEXT)) {
                wizardModel.move(getNextStep());
            }
            wizardModel.fireStateChanged();
        } else {
            wizardModel.fireStateChanged();
            txtUserInput.requestFocus();
        }
    }//GEN-LAST:event_txtUserInputKeyReleased

    private void formFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_formFocusGained
        if (this.isVisible()) {
            txtUserInput.requestFocus();
        }
    }//GEN-LAST:event_formFocusGained
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JTextField txtUserInput;
    // End of variables declaration//GEN-END:variables

    public boolean isCancellable() {
        return true;
    }

    public boolean isFinishable() {
        return getNextStep() == null && txtUserInput.getText().length() > 0;
    }

    @Override
    public boolean canGoNext() {
        return txtUserInput.getText().length() > 0;
    }

    @Override
    public boolean canGoBack() {
        return true;
    }
}
