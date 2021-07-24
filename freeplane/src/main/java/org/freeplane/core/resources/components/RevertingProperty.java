/*
 *  Freeplane - mind map editor
 *  Copyright (C) 2008 Joerg Mueller, Daniel Polansky, Christian Foltin, Dimitry Polivaev
 *
 *  This file is modified by Dimitry Polivaev in 2008.
 *
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 2 of the License, or
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
package org.freeplane.core.resources.components;

import java.awt.Dimension;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import org.freeplane.core.util.TextUtils;

import com.jgoodies.forms.builder.DefaultFormBuilder;

public class RevertingProperty extends PropertyBean implements IPropertyControl {
    private final static String REVERT_RESOURCE = "reset_property_text";
    private static final String TEXT = TextUtils.getText(REVERT_RESOURCE);
	private final JButton revertButton;

	/**
	 */
	public RevertingProperty() {
		super(TEXT);
		revertButton = new JButton(TEXT) {
            private static final long serialVersionUID = 1L;

            @Override
            public Dimension getPreferredSize() {
                Dimension preferredSize = new Dimension(super.getPreferredSize());
                Insets insets = getInsets();
                preferredSize.height -= insets.top + insets.bottom;
                return preferredSize;
            }		    
		};
		revertButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                revertButton.setVisible(! getBooleanValue());
				firePropertyChangeEvent();
			}
		});
	}

	@Override
	public String getValue() {
		return revertButton.isVisible() ? Boolean.TRUE.toString() : Boolean.FALSE.toString();
	}

	public void appendToForm(final DefaultFormBuilder builder) {
	    builder.append(revertButton);
	}

	public void setEnabled(final boolean pEnabled) {
		revertButton.setEnabled(pEnabled);
	}

	@Override
	public void setValue(final String value) {
		final boolean booleanValue = Boolean.parseBoolean(value);
		setValue(booleanValue);
	}

	public void setValue(final boolean booleanValue) {
		revertButton.setVisible(booleanValue);
	}

	public boolean getBooleanValue() {
		return revertButton.isVisible();
	}
}