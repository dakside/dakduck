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

import java.net.URL;
import java.util.StringTokenizer;
import java.util.logging.Logger;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JMenu;
import javax.swing.JMenuBar;

/**
 * Flex UI manager
 * @author LeTuanAnh <tuananh.ke@gmail.com>
 */
public class FlexUIHelper {

    public static final Logger logger = Logger.getLogger(FlexUIHelper.class.getName());

    public static Icon locateIcon(Object context, String path) {
        URL url = context.getClass().getResource(path);
        if (url == null) {
            return null;
        } else {
            return new ImageIcon(url);
        }
    }

    public static Icon locateIcon(URL url) {
        try {
            if (url == null) {
                return null;
            }
            return new ImageIcon(url);
        } catch (Exception ex) {
            logger.warning("Cannot locate icon: " + url);
            return null;
        }
    }

    public static Icon getIcon(Object context) {
        return getIcon(context, "icon.png");
    }

    /**
     * Get icon for module
     * @param context
     * @param iconName
     * @return
     */
    public static Icon getIcon(Object context, String iconName) {
        if (context == null || iconName == null || iconName.trim().isEmpty()) {
            return null;
        }
        String pkg = context.getClass().getPackage().getName();

        URL url = null;
        if (pkg.length() == 0) {
            //default package
            url = context.getClass().getResource("/" + iconName);
        } else {
            url = context.getClass().getResource("/" + pkg.replace('.', '/') + "/" + iconName);
        }

        return (url != null) ? new ImageIcon(url) : null;
    }

    /**
     * Construct menu from path (Root\tParent\tOther)
     * @param path
     * @param pathText
     * @param bar
     * @return
     */
    public static JMenu constructMenu(String path, String pathText, JMenuBar bar) {
        //1. split path
        StringTokenizer pathTokens = new StringTokenizer(path, "\t");
        StringTokenizer pathTextTokens = new StringTokenizer(pathText, "\t");
        if (pathTokens.countTokens() != pathTextTokens.countTokens()
                || pathTokens.countTokens() <= 0) {
            return null;
        }

        //2. create menu
        JMenu menu = null;
        while (pathTokens.hasMoreElements()) {
            RuntimeMenu menuInfo = new RuntimeMenu(pathTokens.nextToken(), pathTextTokens.nextToken());
            if (menu == null) {
                menu = FlexUIHelper.getMenu(menuInfo, bar, true);
            } else {
                menu = FlexUIHelper.getMenu(menuInfo, menu, true);
            }
        }

        return menu;
    }

    public static JMenu getMenu(RuntimeMenu menuInfo, JMenu parentMenu, boolean autoCreate) {
        if (parentMenu == null || menuInfo == null || menuInfo.getName() == null || menuInfo.getText() == null) {
            return null;
        }

        for (int i = 0; i < parentMenu.getMenuComponentCount(); i++) {
            if (menuInfo.getName().equals(parentMenu.getMenuComponent(i).getName())) {
                return (JMenu) parentMenu.getMenuComponent(i);
            }
        }
        //cannot find
        if (autoCreate) {
            return (JMenu) menuInfo.bindTo(parentMenu);

        }
        return null;
    }

    public static JMenu getMenu(RuntimeMenu menuInfo, JMenuBar parentMenu, boolean autoCreate) {
        if (parentMenu == null || menuInfo == null || menuInfo.getName() == null || menuInfo.getText() == null) {
            return null;
        }
        for (int i = 0; i < parentMenu.getMenuCount(); i++) {
            if (menuInfo.getName().equals(parentMenu.getMenu(i).getName())) {
                return (JMenu) parentMenu.getMenu(i);
            }
        }
        //cannot find
        if (autoCreate) {
            return (JMenu) menuInfo.bindTo(parentMenu);
        }
        return null;
    }
}
