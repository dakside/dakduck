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
package org.dakside.duck.appui;

import org.dakside.duck.flexui.FlexUIHelper;
import org.dakside.utils.ResourceCentre;
import java.awt.Component;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTabbedPane;
import org.dakside.duck.plugins.FunctionFacade;
import org.dakside.duck.plugins.FunctionGroup;
import org.dakside.duck.plugins.FunctionPool;

/**
 * Group module panel
 *
 * @author LeTuanAnh <tuananh.ke@gmail.com>
 */
public class GroupModulePanel extends JTabbedPane {
    
    private ResourceCentre rc;
    
    public GroupModulePanel() {
        this("config.modulesUI");
    }
    
    public GroupModulePanel(String rcName) {
        try {
            //default tab placement is left
            setTabPlacement(JTabbedPane.LEFT);
            setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
            // Initialise resource centre
            this.rc = ResourceCentre.getInstance(rcName);
            //autoload?
            //load();
        } catch (Exception ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.WARNING, "Error while loading group module panel", ex);
        }
    }

    public void updateResourceCentre(String rcName){
        this.rc = ResourceCentre.getInstance(rcName);
    }
    
    /**
     * Load available modules
     */
    public void load() {
        load(new FunctionGroup.Priority[0]);
    }
    
    public void load(FunctionGroup.Priority[] priorities) {
        for (FunctionGroup.Priority priority : priorities) {
            FunctionPool.getInstance().setGroupPriority(priority);
        }

        //get all groups
        FunctionGroup[] groups = FunctionPool.getInstance().getAllGroups();
        //System.out.printf("Available groups: %s\n", groups.length);

        // Remove all current tab
        this.removeAll();
        
        //create group tab
        for (FunctionGroup group : groups) {
            FunctionFacade[] currentGroupModules = group.toArray();
            //no need to show empty group
            if (currentGroupModules.length == 0) {
                continue;
            }
            JButton[] groupButtons = new JButton[currentGroupModules.length];
            int i = 0;
            for (FunctionFacade module : currentGroupModules) {
                //create JButton for the command
                groupButtons[i] = createButton(module);
                i++;
            }
            
            Component tab = new ModulePanel(groupButtons);
            Icon icon = getIcon(group.getTitle());
            //localized tab text
            addTab(rc.getString("txt_" + group.getTitle()), icon, tab);
        }
    }

    /**
     * Create command button
     *
     * @param module
     * @return
     */
    private JButton createButton(FunctionFacade module) {
        JButton button = new JButton();
        button.setText(module.getResourceCentre().getString(module.getInfo().Text()));
        button.setToolTipText(module.getResourceCentre().getString(module.getInfo().Description()));
        button.setActionCommand(module.getKey());
        
        button.setName(module.getKey());
        //set button icon if available
        Icon icon = FlexUIHelper.locateIcon(module.getIcon());
        if (icon != null) {
            button.setIcon(icon);
        }
        //add listener (callback to command centre)
        button.removeActionListener(FunctionPool.getInstance());
        button.addActionListener(FunctionPool.getInstance());
        
        return button;
    }

    /**
     * Get icon of a group (return a default icon if cannot found)
     *
     * @param name
     * @return
     */
    private Icon getIcon(String name) {
        if (!rc.containsKey("icon_" + name)) {
            name = "icon_default";
        }
        String iconUrl = rc.getString("icon_" + name);
        URL url = this.getClass().getResource(iconUrl);
        if (url != null) {
            return new ImageIcon(url);
        } else {
            return null;
        }
    }

    /**
     * activate all modules
     */
    public void activate() {
        for (Component c : getComponents()) {
            if (c instanceof ModulePanel) {
                ((ModulePanel) c).activate();
            }
        }
    }

    /**
     * deactivate all modules
     */
    public void deactivate() {
        for (Component c : getComponents()) {
            if (c instanceof ModulePanel) {
                ((ModulePanel) c).deactivate();
            }
        }
    }
}
