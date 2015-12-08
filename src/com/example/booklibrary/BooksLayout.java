package com.example.booklibrary;

import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Button;
import com.vaadin.ui.Grid;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

public class BooksLayout extends VerticalLayout implements View{

	/**
	 * 
	 */
	private static final long serialVersionUID = -903374386545590688L;
	
	TextField filter = new TextField();
	Grid bookList = new Grid();
	Button newBook = new Button("New Book");
	BookService bookService;
	
	// BookForm is an example of a custom component class
    BookForm bookForm;

	public BooksLayout(BookService bookService) {
		// TODO Auto-generated constructor stub
		this.bookService = bookService;
		bookForm = new BookForm(bookService);
	}

	@Override
	public void enter(ViewChangeEvent event) {
		buildLayout();
		configureComponents();
	}
	
	private void configureComponents() {
		
	       newBook.addClickListener(e -> bookForm.edit(new Book()));

	       filter.setInputPrompt("Filter books...");
	       filter.addTextChangeListener(e -> refreshBooks(e.getText()));

	       bookList.setContainerDataSource(new BeanItemContainer<>(Book.class));
	       bookList.setColumnOrder("bookName", "bookDescription", "bookISBN");
	       //bookList.removeColumn("id");
	       //bookList.removeColumn("birthDate");
	       //bookList.removeColumn("phone");
	       bookList.setSelectionMode(Grid.SelectionMode.SINGLE);
	       bookList.addSelectionListener(e
	               -> bookForm.edit((Book) bookList.getSelectedRow()));
	       refreshBooks();
	   }

	   private void buildLayout() {
	       HorizontalLayout actions = new HorizontalLayout(filter, newBook);
	       actions.setWidth("100%");
	       filter.setWidth("100%");
	       actions.setExpandRatio(filter, 1);

	       VerticalLayout left = new VerticalLayout(actions, bookList);
	       left.setSizeFull();
	       bookList.setSizeFull();
	       left.setExpandRatio(bookList, 1);

	       HorizontalLayout mainLayout = new HorizontalLayout(left, bookForm);
	       mainLayout.setSizeFull();
	       mainLayout.setExpandRatio(left, 1);
	       
	       this.addComponent(mainLayout);

	   }

	   void refreshBooks() {
		   refreshBooks(filter.getValue());
	   }

	   private void refreshBooks(String stringFilter) {
	       bookList.setContainerDataSource(new BeanItemContainer<>(
	               Book.class, bookService.findAll(stringFilter)));
	       bookForm.setVisible(false);
	   }

}
