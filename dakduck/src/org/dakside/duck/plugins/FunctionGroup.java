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

import org.dakside.utils.Validator;
import java.util.ArrayList;
import java.util.Comparator;

/**
 *
 * @author LeTuanAnh <tuananh.ke@gmail.com>
 */
public class FunctionGroup implements Comparable<FunctionGroup> {

    public static class Priority {

        public Priority() {
        }

        public Priority(String title, int priority) {
            this.title = title;
            this.priority = priority;
        }

        private String title;
        private int priority;

        /**
         * @return the title
         */
        public String getTitle() {
            return title;
        }

        /**
         * @param title the title to set
         */
        public void setTitle(String title) {
            this.title = title;
        }

        /**
         * @return the priority
         */
        public int getPriority() {
            return priority;
        }

        /**
         * @param priority the priority to set
         */
        public void setPriority(int priority) {
            this.priority = priority;
        }

    }

    private final Priority priority;
    private ArrayList<FunctionFacade> modules;

    public FunctionGroup(String title) {
        priority = new Priority();
        priority.setTitle(title);
    }

    /**
     * @return the modules
     */
    private ArrayList<FunctionFacade> getModules() {
        if (modules == null) {
            modules = new ArrayList<>();
        }
        return modules;
    }

    /**
     * Get all modules as an array
     *
     * @return
     */
    public FunctionFacade[] toArray() {
        return getModules().toArray(new FunctionFacade[0]);
    }

    /**
     * Add module
     *
     * @param module ignored if == null
     */
    void add(FunctionFacade module) {
        if (module != null) {
            getModules().add(module);

        }
    }

    public String getTitle() {
        return this.priority.getTitle();
    }

    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.priority.title = title;
    }

    public static Comparator<FunctionGroup> getComparator() {
        return new Comparator<FunctionGroup>() {

            public int compare(FunctionGroup o1, FunctionGroup o2) {
                int result = Validator.compareByNull(o1, o2);
                if (result == 0) {
                    result = Validator.compareByString(o1.priority.getTitle(), o2.priority.getTitle());
                }
                return result;
            }
        };

    }

    public static Comparator<FunctionGroup> getPriorityComparator() {
        return new Comparator<FunctionGroup>() {

            public int compare(FunctionGroup o1, FunctionGroup o2) {
                int result = Validator.compareByNull(o1, o2);
                if (result == 0) {
                    result = o1.getPriority() - o2.getPriority();
                }
                return result;
            }
        };

    }

    @Override
    public int compareTo(FunctionGroup o) {
        return getComparator().compare(this, o);
    }

    /**
     * @return the priority
     */
    public int getPriority() {
        return this.priority.getPriority();
    }

    /**
     * Priority: the larger number is the lower priority
     *
     * @param priority the PRIORITY to set
     */
    public void setPriority(int priority) {
        this.priority.setPriority(priority);
    }
}
