package com.example.booklibrary;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

/** A start view for navigating to the main view */
public class StartView extends VerticalLayout implements View {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public StartView() {
		setSizeFull();
		Label label = new Label("<h3> Welcome to book store </h3>",ContentMode.HTML);
		addComponent(label);
		setComponentAlignment(label, Alignment.MIDDLE_CENTER);
	}
	@Override
	public void enter(ViewChangeEvent event) {
		
	}
}
