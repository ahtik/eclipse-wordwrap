/*******************************************************************************
 * Copyright (c) 2006-2011 Ahti Kitsik.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Ahti Kitsik
 *******************************************************************************/

package com.ahtik.wordwrap;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.widgets.Event;

public class ToggleHandler extends AbstractHandler {

	public Object execute(ExecutionEvent event) throws ExecutionException {
		if (!(event.getTrigger() instanceof Event)) {
			return null;
		}
		Event ev = (Event) event.getTrigger();
		if (!(ev.widget instanceof StyledText)) {
			return null;
		}
		StyledText w = (StyledText) ev.widget;
		w.setWordWrap(!w.getWordWrap());
		return null;
	}

}
