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
import java.util.Map;

/**
 * System message
 * @author LeTuanAnh <tuananh.ke@gmail.com>
 */
public class Message {

    private String message;
    private Map<String, Object> args;

    public Message(String message) {
        this.message = message;
        this.args = new HashMap<>();
    }

    /**
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * @param message the message to set
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * @return the args
     */
    public Map<String, Object> getArgs() {
        return args;
    }

    /**
     * @param args the args to set
     */
    public void setArgs(Map<String, Object> args) {
        this.args = args;
    }

    public static boolean valid(Message message) {
        return message != null
                && message.message != null
                && !message.message.trim().isEmpty()
                && message.args != null;
    }
}
