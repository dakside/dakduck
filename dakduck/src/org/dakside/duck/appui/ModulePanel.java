/*
 *  Copyright (C) 2009 Le Tuan Anh
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

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.ParallelGroup;
import javax.swing.GroupLayout.SequentialGroup;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneLayout;

/**
 * Module list Panel (Display a list of modules as buttons)
 * @author LeTuanAnh <tuananh.ke@gmail.com>
 */
public class ModulePanel extends JScrollPane {

    private final JButton[] buttons;

    public ModulePanel(JButton[] buttons) {
        this.buttons = buttons;
        initListButtons(buttons);
    }

    /**
     * Create a button list on a panel & add to this panel
     * @param buttons
     */
    private void initListButtons(JButton[] buttons) {
        //create module layout
        JPanel panel = new JPanel();
        GroupLayout layout = new GroupLayout(panel);
        panel.setLayout(layout);

        //horizontal group
        SequentialGroup horizontalGroup = layout.createSequentialGroup().addContainerGap();
        //add buttons
        ParallelGroup horizontalButtonGroup = layout.createParallelGroup(GroupLayout.Alignment.LEADING);
        for (JButton button : buttons) {
            horizontalButtonGroup = horizontalButtonGroup.addComponent(button, GroupLayout.DEFAULT_SIZE, 380, Short.MAX_VALUE);
        }
        //add button sequence to horizontal group
        horizontalGroup = horizontalGroup.addGroup(horizontalButtonGroup).addContainerGap();
        layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(horizontalGroup));

        //vertical group
        SequentialGroup verticalGroup = layout.createSequentialGroup().addContainerGap();
        for (JButton button : buttons) {
            verticalGroup = verticalGroup.addComponent(button, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE);
            verticalGroup = verticalGroup.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED);
        }
        //add button sequence to vertical group
        verticalGroup = verticalGroup.addContainerGap(0, Short.MAX_VALUE);
        layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(verticalGroup));

        //add button panel to scroll pane
        ScrollPaneLayout l = new ScrollPaneLayout();
        l.setVerticalScrollBarPolicy(ScrollPaneLayout.VERTICAL_SCROLLBAR_AS_NEEDED);
        l.setHorizontalScrollBarPolicy(ScrollPaneLayout.HORIZONTAL_SCROLLBAR_NEVER);
        this.setLayout(l);
        this.getViewport().add(panel);
    }

    /**
     * activate module list (enable all buttons)
     */
    public void activate() {
        for (JButton button : buttons) {
            button.setEnabled(true);
        }
    }

    /**
     * de-activate module list (disable all buttons)
     */
    public void deactivate() {
        for (JButton button : buttons) {
            button.setEnabled(false);
        }
    }
}
