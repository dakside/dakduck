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
package org.dakside.duck.appui;

import java.awt.Component;
import java.util.ArrayList;
import java.util.HashSet;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JTabbedPane;
import javax.swing.JToolBar;
import org.dakside.duck.flexui.FlexUIHelper;
import org.dakside.duck.flexui.RuntimeMenu;
import org.dakside.duck.plugins.Command;
import org.dakside.duck.plugins.CommandInvoker;
import org.dakside.duck.plugins.Function;
import org.dakside.duck.plugins.FunctionFacade;
import org.dakside.duck.plugins.FunctionPool;
import org.dakside.duck.plugins.ToolbarContainer;
import org.dakside.utils.ResourceCentre;

/**
 *
 * @author LeTuanAnh <tuananh.ke@gmail.com>
 */
public class DuckFrame {

    private final HashSet<JButton> extButtons = new HashSet<JButton>();
    private final HashSet<Component> extCommands = new HashSet<Component>();
    private JMenuBar menuBar;
    private JToolBar mainToolbar;
    private JTabbedPane mainTabPane;
    private ResourceCentre rc;

    public DuckFrame(JMenuBar menuBar, JToolBar mainToolbar, JTabbedPane mainTabPane, ResourceCentre rc) {
        this.menuBar = menuBar;
        this.mainToolbar = mainToolbar;
        this.mainTabPane = mainTabPane;
        this.rc = rc;
    }

    public void initCommand() {
        FunctionPool server = FunctionPool.getInstance();
        FunctionFacade[] commands = server.getAllCommands();
        for (FunctionFacade command : commands) {
            initCommand(command);
        }
        addCommandToToolbar();
    }

    /**
     * Init each command
     *
     * @param cmdFacade
     * @param method
     */
    public void initCommand(FunctionFacade cmdFacade) {
        //for localization
        ResourceCentre translator = cmdFacade.getResourceCentre();
        Function info = cmdFacade.getInfo();

        switch (cmdFacade.getInfo().Location()) {
            case Function.STARTPAGE:
                //add to start page
                break;
            case Function.MENU: {
                //add to menu
                //1. construct menu holder (parent menu)
                String menuPath = info.Category();
                String menuText = translator.getString(menuPath);
                JMenu menu = FlexUIHelper.constructMenu(menuPath, menuText, menuBar);

                //create child menu item
                String text = translator.getString(info.Text());
                RuntimeMenu leaf = new RuntimeMenu(cmdFacade.getKey(), text, RuntimeMenu.MENUITEM);

                JMenuItem item = leaf.bindTo(menu);
                extCommands.add(item); //add to extension list

                item.setIcon(FunctionPool.getIcon(cmdFacade.getIcon()));
                //set action command
                item.setActionCommand(cmdFacade.getKey());
                item.removeActionListener(FunctionPool.getInstance());
                item.addActionListener(FunctionPool.getInstance());
                break;
            }
            case Function.TOOLBAR: {
                //add to toolbar
                JButton button = new JButton();
                button.setFocusable(false);
                button.setText(cmdFacade.getResourceCentre().getString(cmdFacade.getInfo().Text()));
                button.setName(cmdFacade.getKey());
                button.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
                button.setVerticalTextPosition(javax.swing.SwingConstants.CENTER);
                //set icon if available
                Icon ico = FunctionPool.getIcon(cmdFacade.getIcon());
                if (ico != null) {
                    button.setIcon(ico);
                }
                extButtons.add(button); //add to toolbar button reference
                extCommands.add(button); //add to extension list
                button.setActionCommand(cmdFacade.getKey());
                button.removeActionListener(FunctionPool.getInstance());
                button.addActionListener(FunctionPool.getInstance());
                break;
            }
        }
    }

    /**
     * reload the main toolbar
     */
    private void addCommandToToolbar() {
        mainToolbar.invalidate();
        for (JButton button : extButtons) {
            if (mainToolbar.getComponentIndex(button) < 0) {
                mainToolbar.add(button);
            }
        }
        //jToolBar1.revalidate();
        mainToolbar.repaint();
    }

    private final ArrayList<Component> components = new ArrayList<>();
    private Component activeTab = null;
    private CommandInvoker currentInvoker = null;

    public Component getActiveTab() {
        return activeTab;
    }

    public CommandInvoker getCurrentInvoker() {
        return currentInvoker;
    }

    public void setCurrentInvoker(CommandInvoker currentInvoker) {
        this.currentInvoker = currentInvoker;
    }

    public void invokeCommand(String key) {
        if (currentInvoker != null && currentInvoker.support(key)) {
            Command cmd = currentInvoker.locateCommand(key);
            cmd.invoke(new Object[0]);
        }
    }

    /**
     * Set active state
     *
     * @param isActive
     */
    public void refreshExtensions(boolean isActive) {
        for (Component c : extCommands) {
            c.setEnabled(isActive);
        }
    }

    /**
     * Refresh tab-related buttons on main toolbar
     */
    public void refreshToolbar() {
        //still current tab, no need to reload
        if (activeTab == mainTabPane.getSelectedComponent()) {
            return;
        } else {
            this.activeTab = mainTabPane.getSelectedComponent();
        }

        //clear previous component
        synchronized (components) {
            for (int i = components.size() - 1; i >= 0; i--) {
                //logger.info("Removing component from toolbar");
                mainToolbar.remove(components.get(i));
                components.remove(i);
            }
        }
        //remove previous invoker
        currentInvoker = null;

        if (mainTabPane.getSelectedComponent() instanceof ToolbarContainer) {
            ToolbarContainer tc = (ToolbarContainer) mainTabPane.getSelectedComponent();
            Component[] items = tc.getToolbarItems();
            //logger.info("Adding " + items.length + " buttons");

            //should have a separator to separate custom menu
            if (items.length > 0 && extButtons.size() > 0) {
                JToolBar.Separator sep = new JToolBar.Separator();
                components.add(sep);
                mainToolbar.add(sep);
            }

            for (Component com : items) {
                //logger.info("Adding component to toolbar");
                components.add(com);
                mainToolbar.add(com);
            }

            //get invoker to activate/deactivate standard toolbar buttons
            //(new/save/refresh etc)
            currentInvoker = tc.getInvoker();
        }

        //refreshButtons();
        this.mainToolbar.revalidate();
        this.mainToolbar.repaint();

        //clear status
        //setStatusMessage("");
    }

    //<editor-fold defaultstate="collapsed" desc="Tabs and view">
    /**
     * Add a component to tab
     *
     * @param title
     * @param c
     */
    public void show(String title, Component c) {
        //add component to tab
        Component[] views = mainTabPane.getComponents();
        boolean exists = false;
        for (int i = 0; i < views.length; i++) {
            if (views[i] == c) {
                exists = true;
            }
        }
        if (!exists) {
            mainTabPane.add(title, c);
        }
        //select component
        mainTabPane.setSelectedComponent(c);
    }
    //</editor-fold>
    
    
    /**
     * set icon of a button
     *
     * @param c
     * @param iconName
     */
    public void setIcon(JButton c, String iconName) {
        Icon icon = FlexUIHelper.locateIcon(this, rc.getString(iconName));
        if (icon != null) {
            c.setIcon(icon);
        }
    }

    /**
     * set icon of a button
     *
     * @param c
     * @param iconName
     */
    public void setIcon(JMenu c, String iconName) {
        Icon icon = FlexUIHelper.locateIcon(this, rc.getString(iconName));
        if (icon != null) {
            c.setIcon(icon);
        }
    }
}
