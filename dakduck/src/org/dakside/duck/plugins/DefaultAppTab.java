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
package org.dakside.duck.plugins;

import java.awt.Component;
import javax.swing.Icon;

/**
 * AppTab container
 *
 * @author LeTuanAnh <tuananh.ke@gmail.com>
 */
public class DefaultAppTab implements AppTab {

    private final Icon icon;
    private final String title;
    private final String description;
    private final Component view;

    public DefaultAppTab(Icon icon, String title, String description, Component view) {
        this.icon = (icon == null) ? null : icon;
        this.title = title;
        this.description = description;
        this.view = view;
    }

    /**
     *
     * @return
     */
    @Override
    public Icon getIcon() {
        return icon;
    }

    @Override
    public String getTitle() {
        return this.title;
    }

    @Override
    public String getDescription() {
        return this.description;
    }

    @Override
    public Component getView() {
        return this.view;
    }
}
