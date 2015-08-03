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

import org.dakside.utils.ResourceCentre;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Command facade, to handle module reference, invoke command, generate key, etc
 * @author LeTuanAnh <tuananh.ke@gmail.com>
 */
public class FunctionFacade {

    private Method method;
    private Function info;
    private Object module;
    private String key;

    /**
     * Facade constructor
     * @param method
     * @param module
     */
    public FunctionFacade(Method method, Object module) {
        if (method == null) {
            throw new RuntimeException("Null command detected.");
        }
        this.method = method;
        if (this.method.getParameterTypes().length != 0) {
            throw new RuntimeException("Complex command is not supported");
        }

        this.module = module;
        this.info = method.getAnnotation(Function.class);
        if (this.info == null) {
            throw new RuntimeException("Invalid command detected.");
        }
        //generate key
        this.key = this.method.getClass().getName() + "#" + this.method.getName();
    }

    public ResourceCentre getResourceCentre() {
        return ResourceCentre.getInstance(module);
    }

    public URL getIcon() {
        //get icon as absolute path
        String path = getResourceCentre().getString(info.IconPath());
        URL url = module.getClass().getResource(path);
        if (url == null) {
            //get as relative path
            url = FunctionPool.getIcon(module, path);
        }
        if (url == null) {
            //still null -> get default icon
            url = FunctionPool.getIcon(module);
        }
        return url;
    }

    public Object invoke() {
        try {
            return this.method.invoke(module);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(FunctionFacade.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalArgumentException ex) {
            Logger.getLogger(FunctionFacade.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvocationTargetException ex) {
            Logger.getLogger(FunctionFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    /**
     * @return the info
     */
    public Function getInfo() {
        return info;
    }

    /**
     * @return the key
     */
    public String getKey() {
        return key;
    }

    /**
     * Unload this module
     */
    public void unload() {
        //
        if (module instanceof Unloadable) {
            ((Unloadable) module).unload();
        }
    }
}
