/*
 * Copyright (C) 2015 chibiame
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.dakside.duck.appui;

import java.awt.event.ActionListener;
import javax.swing.JPanel;

/**
 *
 * @author chibiame
 */
public interface StartPage {

    JPanel getView();

    boolean isActivated();

    void addActiveStateChangedListener(ActionListener l);

    void removeActiveStateChangedListener(ActionListener l);

    GroupModulePanel getGroupModulePanel();
}
