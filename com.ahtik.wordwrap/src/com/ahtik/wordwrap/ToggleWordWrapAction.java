/*******************************************************************************
 * Copyright (c) 2006 Ahti Kitsik.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Ahti Kitsik
 *******************************************************************************/

package com.ahtik.wordwrap;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.IActionDelegate;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.texteditor.ITextEditor;

public class ToggleWordWrapAction implements IObjectActionDelegate {
	 
	ITextEditor fEditor;

	/**
	 * @see IObjectActionDelegate#setActivePart(IAction, IWorkbenchPart)
	 */
	public void setActivePart(IAction action, IWorkbenchPart targetPart) {
		fEditor = configureActive(action, targetPart);		
	}

	static ITextEditor configureActive(IAction action, IWorkbenchPart targetPart) {
		ITextEditor editor = null;
		if (targetPart instanceof ITextEditor && action instanceof Action) {
			editor = (ITextEditor) targetPart;
			Action act = (Action) action;
			Object adapter = editor.getAdapter(Control.class);			
			if (adapter instanceof StyledText) {
				StyledText text = (StyledText) adapter;
				boolean currentWrap = text.getWordWrap();				
				act.setChecked(currentWrap);
			}			
		}
		return editor;
	}

	/**
	 * @see IActionDelegate#run(IAction)
	 */
	public void run(IAction action) {
		if (fEditor!=null) {
			toggle(action, fEditor);			
		}
	}

	static void toggle(IAction action, ITextEditor editor) {
		StyledText text = (StyledText) editor.getAdapter(Control.class);
		boolean currentWrap = text.getWordWrap();
		text.setWordWrap(!currentWrap);
		if (action instanceof Action) {
			Action act = (Action) action;
			act.setChecked(!currentWrap);
		}
	}

	public void selectionChanged(IAction action, ISelection selection) {
	}

}
