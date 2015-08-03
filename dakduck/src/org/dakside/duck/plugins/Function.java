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

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Command annotation (module command)
 * @author LeTuanAnh <tuananh.ke@gmail.com>
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Function {

    public static final int TOOLBAR = 1;
    public static final int MENU = 2;
    public static final int STARTPAGE = 3;
    public static final String DEFAULTCATEGORYSTRING = "Extensions";

    String Text();
    String Description() default "";
    /**
     * Location of the command (menu, startpage, toolbar)
     * @return
     */
    int Location() default STARTPAGE;
    String Category() default DEFAULTCATEGORYSTRING;
    String IconPath() default "icon.png";
}
