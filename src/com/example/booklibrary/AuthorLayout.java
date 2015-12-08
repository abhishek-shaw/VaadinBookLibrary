package com.example.booklibrary;

import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Button;
import com.vaadin.ui.Grid;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

public class AuthorLayout extends VerticalLayout implements View{

	/**
	 * 
	 */
	private static final long serialVersionUID = -903374386545590688L;
	
	TextField filter = new TextField();
	Grid authorList = new Grid();
	Button newAuthor = new Button("New Author");
	AuthorService authorService;
	
	// BookForm is an example of a custom component class
    AuthorForm authorForm;

	public AuthorLayout(AuthorService authorService) {
		// TODO Auto-generated constructor stub
		this.authorService = authorService;
		authorForm = new AuthorForm(authorService);
	}

	@Override
	public void enter(ViewChangeEvent event) {
		buildLayout();
		configureComponents();
	}
	
	private void configureComponents() {
		
		newAuthor.addClickListener(e -> authorForm.edit(new Author()));

	       filter.setInputPrompt("Filter books...");
	       filter.addTextChangeListener(e -> refreshAuthors(e.getText()));

	       authorList.setContainerDataSource(new BeanItemContainer<>(Author.class));
	       authorList.setColumnOrder("authorName", "authorEmail", "dateOfBirth");
	       //bookList.removeColumn("id");
	       //bookList.removeColumn("birthDate");
	       //bookList.removeColumn("phone");
	       authorList.setSelectionMode(Grid.SelectionMode.SINGLE);
	       authorList.addSelectionListener(e
	               -> authorForm.edit((Author) authorList.getSelectedRow()));
	       refreshAuthors();
	   }

	   private void buildLayout() {
	       HorizontalLayout actions = new HorizontalLayout(filter, newAuthor);
	       actions.setWidth("100%");
	       filter.setWidth("100%");
	       actions.setExpandRatio(filter, 1);

	       VerticalLayout left = new VerticalLayout(actions, authorList);
	       left.setSizeFull();
	       authorList.setSizeFull();
	       left.setExpandRatio(authorList, 1);

	       HorizontalLayout mainLayout = new HorizontalLayout(left, authorForm);
	       mainLayout.setSizeFull();
	       mainLayout.setExpandRatio(left, 1);
	       
	       this.addComponent(mainLayout);

	   }

	   void refreshAuthors() {
		   refreshAuthors(filter.getValue());
	   }

	   private void refreshAuthors(String stringFilter) {
	       authorList.setContainerDataSource(new BeanItemContainer<>(
	               Author.class, authorService.findAll(stringFilter)));
	       authorForm.setVisible(false);
	   }

}