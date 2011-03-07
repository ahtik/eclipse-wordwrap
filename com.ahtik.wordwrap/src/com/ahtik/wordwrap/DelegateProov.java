package com.ahtik.wordwrap;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.IWorkbenchWindowActionDelegate;
import org.eclipse.ui.texteditor.ITextEditor;

public class DelegateProov implements IWorkbenchWindowActionDelegate {

	
	private IWorkbenchWindow fWindow;

	public void dispose() {
	
	}

	public void init(IWorkbenchWindow window) {
		fWindow = window;
	}

	public void run(IAction action) {
		if (fWindow!=null && fWindow.getActivePage()!=null) {
			IEditorPart editor = fWindow.getActivePage().getActiveEditor();
			
			if (editor instanceof ITextEditor) {
				ToggleWordWrapAction.toggle(action, (ITextEditor) editor);
			}
		}
	}

	public void selectionChanged(IAction action, ISelection selection) {
		
		if (selection instanceof org.eclipse.jface.text.ITextSelection) {
			action.setEnabled(true);
		} else {
			action.setEnabled(false);
		}

		if (fWindow!=null && fWindow.getActivePage()!=null) {
			IEditorPart editor = fWindow.getActivePage().getActiveEditor();
			
			if (editor instanceof ITextEditor) {
				ToggleWordWrapAction.configureActive(action, editor);
			}
		}

	}

}
