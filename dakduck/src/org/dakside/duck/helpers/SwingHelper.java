/*
 *  Copyright (C) 2009 Le Tuan Anh
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
package org.dakside.duck.helpers;

import org.dakside.utils.ResourceCentre;
import java.io.File;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 * Swing common functions
 *
 * @author LeTuanAnh <tuananh.ke@gmail.com>
 */
public class SwingHelper {

    private static final ResourceCentre rc = ResourceCentre.getInstance(new SwingHelper());

    public static void info(String message) {
        info(message, "Information");
    }

    public static void info(String message, String title) {
        JOptionPane.showMessageDialog(null, message, title, JOptionPane.INFORMATION_MESSAGE);
    }

    public static void alert(String message) {
        alert(message, "Warning");
    }

    public static void alert(String message, String title) {
        JOptionPane.showMessageDialog(null, message, title, JOptionPane.ERROR_MESSAGE);
    }

    /**
     *
     * @param combo
     * @param model
     */
    public static void initCombobox(JComboBox combo, Object[] model, Object selectedItem) {
        combo.removeAllItems();
        for (Object obj : model) {
            combo.addItem(obj);
        }

        comboSelect(combo, selectedItem);
        if (combo.getSelectedItem() == null && combo.getItemCount() > 0) {
            combo.setSelectedIndex(0);
        }
    }

    public static void initCombobox(JComboBox combo, Object[] model) {
        initCombobox(combo, model, null);
    }

    /**
     * Set combo selected item
     *
     * @param combo
     * @param item
     */
    public static void comboSelect(JComboBox combo, Object item) {
        combo.setSelectedItem(item);
    }

    /**
     * Ask user to save data to a file<br/>
     * Notify user if file exists.
     *
     * @param currentFile
     * @return null if cannot save or user cancelled save command
     */
    public static File askSave(String currentFile) {
        JFileChooser chooser = new JFileChooser();
        chooser.setSelectedFile(new File(currentFile));
        if (chooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
            File filePath = chooser.getSelectedFile();
            if (filePath.exists() && filePath.canWrite()) {
                //delete if exist
                //TODO enable localization via properties file
                int status = JOptionPane.showConfirmDialog(chooser,
                        rc.getString("savefile_existed"),
                        rc.getString("savefile_existed_header"),
                        JOptionPane.YES_NO_OPTION);
                if (status == JOptionPane.YES_OPTION && filePath.isFile() && filePath.canWrite()) {
                    filePath.delete();
                } else {
                    return null;
                }
            }
            return filePath;
        }
        return null;
    }

    /**
     * Open file to read (and write if requested)
     *
     * @param currentFile
     * @param openForReadOnly false if user want to write to file also
     * @return null if cannot open or not meet user criteria
     */
    public static File askOpen(String currentFile, boolean openForReadOnly) {
        JFileChooser chooser = new JFileChooser();
        chooser.setSelectedFile(new File(currentFile));
        if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            File filePath = chooser.getSelectedFile();
            if (filePath.exists() && filePath.canRead()
                    && (openForReadOnly || filePath.canWrite())) {
                return filePath;
            }
        }
        return null;
    }

    /**
     * Open file for read-only
     *
     * @param currentFile
     * @return
     */
    public static File askOpen(String currentFile) {
        return askOpen(currentFile, true);
    }

    public static boolean confirm(String string) {
        return JOptionPane.showConfirmDialog(null, string, rc.getString("confirmation"), JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION;
    }

}
