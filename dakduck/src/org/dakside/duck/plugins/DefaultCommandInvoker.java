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

import java.util.HashMap;

/**
 * Default command invoker
 * @author LeTuanAnh <tuananh.ke@gmail.com>
 */
public class DefaultCommandInvoker implements CommandInvoker {

    private HashMap<String, Command> commandMap = new HashMap<String, Command>();
    private HashMap<String, Boolean> commandState = new HashMap<String, Boolean>();

    //add a command
    public final void add(String key, Command cmd) {
        commandMap.put(key, cmd);
        commandState.put(key, true);
    }

    public final Command locateCommand(String commandKey) {
        return commandMap.get(commandKey);
    }

    public final boolean support(String commandKey) {
        return commandMap.get(commandKey) != null
                && commandState.containsKey(commandKey)
                && commandState.get(commandKey);
    }

    public final void setEnabled(String commandKey, boolean enabled) {
        if (commandState.containsKey(commandKey)) {
            commandState.put(commandKey, enabled);
            //auto refresh buttons
            AppCentral.getAPIDelegate().refreshButtons();
        }
    }
}
