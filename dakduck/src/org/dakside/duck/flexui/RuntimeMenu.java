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

package org.dakside.duck.flexui;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

/**
 *
 * @author LeTuanAnh <tuananh.ke@gmail.com>
 */
public class RuntimeMenu {

    public static final int MENU = 1;
    public static final int MENUITEM = 2;
    private String name;
    private String text;
    private int type;

    public RuntimeMenu(String name, String text) {
        setName(name);
        setText(text);
        setType(MENU);
    }

    public RuntimeMenu(String name, String text, int type) {
        setName(name);
        setText(text);
        setType(type);
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the text
     */
    public String getText() {
        return text;
    }

    /**
     * @param text the text to set
     */
    public void setText(String text) {
        this.text = text;
    }

    public JMenuItem toJMenuItem() {
        switch (getType()) {
            case MENU: {
                JMenu menu = new JMenu(getText());
                menu.setName(getName());
                return menu;
            }
            default: {
                JMenuItem menu = new JMenuItem(getText());
                menu.setName(getName());
                return menu;
            }
        }
    }

    public JMenuItem bindTo(JMenu parent) {
        for (int i = 0; i < parent.getItemCount(); i++) {
            if (this.getName().equals(parent.getItem(i).getName())) {
                //cannot add with same name
                return parent.getItem(i);
            }
        }
        JMenuItem item = this.toJMenuItem();
        parent.add(item);
        parent.revalidate();
        return item;
    }

    public JMenuItem bindTo(JMenuBar parent){
        for (int i = 0; i < parent.getMenuCount(); i++) {
            if (this.getName().equals(parent.getMenu(i).getName())) {
                //cannot add with same name
                return parent.getMenu(i);
            }
        }
        JMenuItem item = this.toJMenuItem();
        parent.add(item);
        parent.revalidate();
        return item;
    }

    /**
     * @return the type
     */
    public int getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(int type) {
        this.type = type;
    }
}
