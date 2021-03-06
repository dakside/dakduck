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

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Logger;
import org.dakside.duck.appui.DuckFrame;
import org.dakside.duck.appui.StartPage;
import org.dakside.duck.helpers.SwingHelper;
import org.dakside.duck.plugins.AppCentralAPI;
import org.dakside.duck.plugins.AppTab;
import org.dakside.duck.plugins.Message;
import org.dakside.utils.Localizable;
import org.dakside.utils.ResourceCentre;

/**
 * Main Application GUI
 *
 * @author LeTuanAnh <tuananh.ke@gmail.com>
 */
public class MainFrame extends javax.swing.JFrame implements AppCentralAPI, Localizable {

    private final ResourceCentre rc = ResourceCentre.getInstance(this);
    private static final Logger logger = Logger.getLogger(MainFrame.class.getName());
    private final DuckFrame duckFrame;
    private StartPage startPage = null;

    /**
     * Creates new form MainFrame
     */
    public MainFrame() {
        initComponents();

        duckFrame = new DuckFrame(menuBar, mainToolbar, mainTabPane, rc, getStartPage());

        duckInit();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mainToolbar = new javax.swing.JToolBar();
        btnStartPage = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JToolBar.Separator();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jToolBar1 = new javax.swing.JToolBar();
        statusMessageLabel = new javax.swing.JLabel();
        mainTabPane = new javax.swing.JTabbedPane();
        menuBar = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        mnuHelp = new javax.swing.JMenu();
        mnuLicense = new javax.swing.JMenuItem();
        mnuAbout = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        mainToolbar.setFloatable(false);
        mainToolbar.setRollover(true);

        btnStartPage.setText("Start Page");
        btnStartPage.setFocusable(false);
        btnStartPage.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnStartPage.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnStartPage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnStartPageActionPerformed(evt);
            }
        });
        mainToolbar.add(btnStartPage);
        mainToolbar.add(jSeparator1);

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/dakside/duck/demo/resources/tb_new_16x16.png"))); // NOI18N
        jButton2.setText("New Profile");
        jButton2.setFocusable(false);
        jButton2.setHorizontalAlignment(javax.swing.SwingConstants.LEADING);
        jButton2.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        mainToolbar.add(jButton2);

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/dakside/duck/demo/resources/tb_save_16x16.png"))); // NOI18N
        jButton3.setText("Save");
        jButton3.setFocusable(false);
        jButton3.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        mainToolbar.add(jButton3);

        jToolBar1.setFloatable(false);
        jToolBar1.setRollover(true);

        statusMessageLabel.setText("Status Text");
        jToolBar1.add(statusMessageLabel);

        menuBar.setName(""); // NOI18N

        jMenu1.setText("File");
        menuBar.add(jMenu1);

        mnuHelp.setText("Help");

        mnuLicense.setText("License");
        mnuHelp.add(mnuLicense);

        mnuAbout.setText("About Us");
        mnuHelp.add(mnuAbout);

        menuBar.add(mnuHelp);

        setJMenuBar(menuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mainToolbar, javax.swing.GroupLayout.DEFAULT_SIZE, 632, Short.MAX_VALUE)
            .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(mainTabPane)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(mainToolbar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(mainTabPane, javax.swing.GroupLayout.DEFAULT_SIZE, 322, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnStartPageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnStartPageActionPerformed
        showStartPage();
    }//GEN-LAST:event_btnStartPageActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnStartPage;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JToolBar.Separator jSeparator1;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JTabbedPane mainTabPane;
    private javax.swing.JToolBar mainToolbar;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JMenuItem mnuAbout;
    private javax.swing.JMenu mnuHelp;
    private javax.swing.JMenuItem mnuLicense;
    private javax.swing.JLabel statusMessageLabel;
    // End of variables declaration//GEN-END:variables

    /**
     * Create a StartPage if it doesn't exist
     *
     * @return
     */
    public StartPage getStartPage() {
        if (startPage == null) {
            startPage = new DemoStartPage();
            startPage.addActiveStateChangedListener(new ActionListener() {

                public void actionPerformed(ActionEvent e) {
                    refreshExtensions();
                }
            });
        }
        return startPage;
    }

    /**
     * Show start page
     */
    public void showStartPage() {
        duckFrame.refreshExtensions();
        duckFrame.show(rc.getString("Start_Page"), getStartPage().getView());
    }

    /**
     * Reload extensions
     */
    private void refreshExtensions() {
        duckFrame.refreshExtensions();
    }

    private void duckInit() {
        //init commands
        duckFrame.initCommand();

        //show startup page
        showStartPage();

        //refresh extensions
        refreshExtensions();

        //localization
        localize();
    }

    @Override
    public void showView(Object view) {
        if (view instanceof AppTab) {
            AppTab m = (AppTab) view;
            duckFrame.show(m.getTitle(), m.getView());
        }
    }

    //<editor-fold defaultstate="collapsed" desc="Com interaction">
    @Override
    public void popup(String message) {
        SwingHelper.alert(message, rc.getString("Warning"));
    }

    @Override
    public void setStatusMessage(String text) {
        statusMessageLabel.setText(text);
    }

    @Override
    public void refreshButtons() {
//        if (duckFrame.getCurrentInvoker() != null) {
//            btnNew.setEnabled(duckFrame.getCurrentInvoker().support(AppConstants.Commands.NEW));
//            btnSave.setEnabled(duckFrame.getCurrentInvoker().support(AppConstants.Commands.SAVE));
//            btnDelete.setEnabled(duckFrame.getCurrentInvoker().support(AppConstants.Commands.DELETE));
//            btnRefresh.setEnabled(duckFrame.getCurrentInvoker().support(AppConstants.Commands.REFRESH));
//        } else {
//            btnNew.setEnabled(false);
//            btnSave.setEnabled(false);
//            btnDelete.setEnabled(false);
//            btnRefresh.setEnabled(false);
//        }
    }

    @Override
    public void setAppTitle(String text) {
        //prefix + " - " + text
        ResourceCentre apprc = ResourceCentre.getInstance(this);
        if (text == null || text.trim().isEmpty()) {
            this.setTitle(apprc.getString("Application.title"));
        } else {
            this.setTitle(apprc.getString("Application.title") + " - " + text);
        }
    }

    @Override
    public Object sendMessage(Message message) {
        return null;
    }

    //</editor-fold>
    @Override
    public void localize() {
        // TODO: Add localization codes
    }

}
