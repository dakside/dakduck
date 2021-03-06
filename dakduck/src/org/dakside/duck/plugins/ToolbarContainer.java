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

/**
 * Contains items to display on toolbar
 * @author LeTuanAnh <tuananh.ke@gmail.com>
 */
public interface ToolbarContainer {
    /**
     * Get command invoker to interact with this view
     * @return
     */
    public CommandInvoker getInvoker();
    public Component[] getToolbarItems();
}
