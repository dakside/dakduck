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

/**
 *
 * @author LeTuanAnh <tuananh.ke@gmail.com>
 */
public class AppCentral {

    private static AppCentralAPI mainForm;

    private static AppCentralAPI getInstance() {
        return mainForm;
    }

    public static void initApp(AppCentralAPI mainForm) {
        if (AppCentral.mainForm == null) {
            AppCentral.mainForm = mainForm;
        } else {
            System.out.println("AppCentral cannot be initialized");
        }
    }

    private static AppCentralInvoker singletonInvoker;

    public static synchronized AppCentralAPI getAPIDelegate() {
        if (singletonInvoker == null) {
            if (getInstance() instanceof AppCentralAPI) {
                singletonInvoker = new AppCentralInvoker((AppCentralAPI) getInstance());
            } else {
                return null;
            }
        }
        return singletonInvoker;
    }
}
