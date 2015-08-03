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
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Icon;
import javax.swing.ImageIcon;

/**
 * Command manager (lookup, invokes and manages function, modules)
 *
 * @author LeTuanAnh <tuananh.ke@gmail.com>
 */
public class FunctionPool implements ActionListener {

    private final HashMap<String, FunctionFacade> commandMap;
    private final ArrayList<FunctionGroup> groups = new ArrayList<>();
    private final HashMap<Class, Object> objectPool;
    private static FunctionPool instance = null;
    private static final Logger logger = Logger.getLogger(FunctionPool.class.getName());

    public static synchronized FunctionPool getInstance() {
        if (instance == null) {
            instance = new FunctionPool();
        }
        return instance;
    }

    /**
     * Construct command server
     */
    private FunctionPool() {
        commandMap = new HashMap<>();
        objectPool = new HashMap<>();
    }

    /**
     * Get all registered command
     *
     * @return
     */
    public FunctionFacade[] getAllCommands() {
        return commandMap.values().toArray(new FunctionFacade[0]);
    }

    /**
     * callback from control
     *
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e == null) {
            return;
        }
        String command = e.getActionCommand();
        //if existed
        if (commandMap.containsKey(command)) {
            //invoke command
            try {
                FunctionFacade cmd = commandMap.get(command);
                Object result = cmd.invoke();
                if (result instanceof AppTab) {
                    //TODO: fix this to make it work with old Module definition
                    AppCentral.getAPIDelegate().showView(result);
                }

                //Component also can bind
                if (result instanceof Component) {

                    AppCentralAPI api = AppCentral.getAPIDelegate();
                    DefaultAppTab tab = new DefaultAppTab(getIcon(cmd.getIcon()),
                            cmd.getResourceCentre().getString(cmd.getInfo().Text()),
                            cmd.getResourceCentre().getString(cmd.getInfo().Description()),
                            (Component) result);
                    api.showView(tab);
                }
            } catch (Exception ex) {
                logger.log(Level.WARNING, "An error happened while calling a command", ex);
            }
        } else {
            logger.log(Level.INFO, "Invalid method callback (action={0})", command);
        }
    }

    /**
     * Lookup a singleton instance of a class<br/>
     * Create new instance if cannot be found.
     *
     * @param aClass
     * @return
     */
    public Object allocModuleObject(Class aClass) {
        if (objectPool.containsKey(aClass)) {
            return objectPool.get(aClass);
        } else {
            synchronized (objectPool) {
                try {
                    Object obj = aClass.newInstance();
                    objectPool.put(aClass, obj);
                    return obj;
                } catch (InstantiationException | IllegalAccessException ex) {
                    logger.log(Level.SEVERE, null, ex);
                }
            }
        }
        return null;
    }

    /**
     * Scan for module (in JAR files)
     */
    public void scan() {
        String[] modules = ResourceCentre.getInstance("config.modules").getLines("modules");
        scan(modules);
        //initModule(TestModule.class.getName());
    }

    /**
     * Custom load a list of modules
     *
     * @param modules
     */
    public void scan(String[] modules) {
        try {
            for (String module : modules) {
                initModule(module);
            }
        } catch (Exception e) {
        }
    }

    /**
     * Unload all commands
     */
    public void unload() {
        FunctionFacade[] cmds = getAllCommands();
        for (FunctionFacade cmd : cmds) {
            cmd.unload();
        }
    }

    private void initModule(String className) {
        try {
            logger.log(Level.INFO, "Loading module: {0}", className);
            initModule(Class.forName(className));
            logger.log(Level.INFO, "Loaded module: {0}", className);
        } catch (ClassNotFoundException ex) {
            logger.log(Level.SEVERE, "Module cannot be found (name = {0})", className);
        }
    }

    public FunctionGroup[] getAllGroups() {
        FunctionGroup[] grps = this.groups.toArray(new FunctionGroup[0]);
        //sort by priority
        Arrays.sort(grps, FunctionGroup.getPriorityComparator());
        return grps;
    }

    /**
     * get group by name
     *
     * @param name
     * @return
     */
    private FunctionGroup getGroup(String name) {
        FunctionGroup group = new FunctionGroup(name);

        int idx = Collections.binarySearch(groups, group);
        if (idx < 0) {
            //not exist
            groups.add(group);
            Collections.sort(groups, FunctionGroup.getComparator());
            return group;
        } else {
            return groups.get(idx);
        }
    }

    /**
     * Search all methods inside a class to detect command
     *
     * @param aClass
     */
    private void initModule(Class aClass) {
        Method[] methods = aClass.getMethods();
        for (Method method : methods) {
            if (method.isAnnotationPresent(Function.class)) {
                try {
                    //this is a command
                    FunctionFacade cf = new FunctionFacade(method, allocModuleObject(aClass));
                    commandMap.put(cf.getKey(), cf);

                    //if on startpage then add to group
                    if (cf.getInfo().Location() == Function.STARTPAGE) {
                        getGroup(cf.getInfo().Category()).add(cf);
                    }

                    logger.log(Level.INFO, "Loaded command: {0}", cf.getInfo().Text());
                } catch (RuntimeException rex) {
                    logger.log(Level.WARNING, "Cannot init module", rex);
                }
            }
        }
    }

    /**
     * Set group priority
     *
     * @param name
     * @param priority
     */
    public void setGroupPriority(String name, int priority) {
        getGroup(name).setPriority(priority);
    }

    public void setGroupPriority(FunctionGroup.Priority priority) {
        setGroupPriority(priority.getTitle(), priority.getPriority());
    }

    public static URL getIcon(Object context) {
        return getIcon(context, "icon.png");
    }

    /**
     * Get icon for module
     *
     * @param context
     * @param iconName
     * @return
     */
    public static URL getIcon(Object context, String iconName) {
        if (context == null || iconName == null || iconName.trim().isEmpty()) {
            return null;
        }
        String pkg = context.getClass().getPackage().getName();

        URL url;
        if (pkg.length() == 0) {
            //default package
            url = context.getClass().getResource("/" + iconName);
        } else {
            url = context.getClass().getResource("/" + pkg.replace('.', '/') + "/" + iconName);
        }

        return url;
    }

    public static Icon getIcon(URL url) {
        return (url != null) ? new ImageIcon(url) : null;
    }
}
