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

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author LeTuanAnh <tuananh.ke@gmail.com>
 */
public class AppCentralInvoker implements AppCentralAPI {

    private static final Logger logger = Logger.getLogger(AppCentralInvoker.class.getName());
    private AppCentralAPI innerObject;

    public AppCentralInvoker(AppCentralAPI obj) {
        innerObject = obj;
    }

    public void showView(Object view) {
        try {
            innerObject.showView(view);
        } catch (Exception ex) {
            logger.log(Level.WARNING, "Cannot bind view", ex);
        }
    }

    public void popup(String message) {
        try {
            innerObject.popup(message);
        } catch (Exception ex) {
            logger.log(Level.WARNING, "Cannot show popup", ex);
        }
    }

    public void setStatusMessage(String text) {
        try {
            innerObject.setStatusMessage(text);
        } catch (Exception ex) {
            logger.log(Level.WARNING, "Cannot show text on status bar", ex);
        }
    }

    public void setAppTitle(String text) {
        try {
            innerObject.setAppTitle(text);
        } catch (Exception ex) {
            logger.log(Level.WARNING, "Cannot show text on title bar", ex);
        }
    }

    public void refreshButtons() {
        try {
            innerObject.refreshButtons();
        } catch (Exception ex) {
            logger.log(Level.WARNING, "Cannot refresh buttons", ex);
        }
    }

    @Override
    public Object sendMessage(Message message) {
        try {
            return innerObject.sendMessage(message);
        } catch (Exception ex) {
            logger.log(Level.WARNING, "Cannot refresh buttons", ex);
        }
        return false;
    }
}
