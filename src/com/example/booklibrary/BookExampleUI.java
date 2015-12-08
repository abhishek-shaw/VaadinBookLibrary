package com.example.booklibrary;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Button;
import com.vaadin.ui.Grid;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Label;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.MenuBar.MenuItem;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
@Theme("booklibrary")
public class BookExampleUI extends UI {
	
	Navigator navigator;
	
	VerticalLayout mainLayout;
	HorizontalLayout headerLayout;
	HorizontalLayout menuLayout;
	HorizontalLayout contentLayout;
	HorizontalLayout footerLayout;
	
	MenuBar barmenu;
	MenuBar.Command mycommand;
	
	
	BookService bookService = BookService.createDemoService();
	
	AuthorService authorService = AuthorService.createDemoService();

	@WebServlet(value = "/*", asyncSupported = true)
	@VaadinServletConfiguration(productionMode = false, ui = BookExampleUI.class)
	public static class Servlet extends VaadinServlet {
	}

	@Override
	protected void init(VaadinRequest request) {
		buildLayout();
		configureNavigator();
		configureComponents();
		
	}
		
	private void buildLayout() {
		
		mainLayout = new VerticalLayout();
		headerLayout = new HorizontalLayout();
		menuLayout = new HorizontalLayout();
		contentLayout = new HorizontalLayout();
		footerLayout = new HorizontalLayout();
		
		mainLayout.addComponent(headerLayout);
		mainLayout.addComponent(menuLayout);
		mainLayout.addComponent(contentLayout);
		mainLayout.addComponent(footerLayout);
		
		setContent(mainLayout); 
		
	}
	
	private void configureComponents() {
		
		configureHeaderLayout();
		configureMenuLayout();
		
		contentLayout.setSizeFull();
		footerLayout.setSizeFull();
		
		contentLayout.addComponent(new Label("<h2>Content Layout</h2>", ContentMode.HTML));
		footerLayout.addComponent(new Label("<h2>Footer Layout</h2>", ContentMode.HTML));
		
	}
	
	private void configureNavigator(){
		// Create a navigator to control the views
		navigator = new Navigator(this, contentLayout);
		// Create and register the views
		navigator.addView("", new StartView());
		navigator.addView("listBooks", new BooksLayout(bookService));
		navigator.addView("addBook", new BookForm(bookService));
		navigator.addView("listAuthors", new AuthorLayout(authorService));
		navigator.addView("addAuthor", new AuthorForm(authorService));
		
	}
	
	private void configureHeaderLayout(){
		headerLayout.setSizeFull();
		headerLayout.addComponent(new Label("<center><h2>Simple Book Store</h2></center>", ContentMode.HTML));
	}
	
	private void configureMenuLayout(){
		menuLayout.setSizeFull();
		barmenu = new MenuBar();
		barmenu.setSizeFull();
		
		// Define a common menu command for all the menu items.
		mycommand = new MenuBar.Command(){
			@Override
			public void menuSelected(MenuItem selectedItem) {
				if(selectedItem.getText().equals("Add Book")){
					navigator.navigateTo("addBook");
				}
				if(selectedItem.getText().equals("List Books")){
					navigator.navigateTo("listBooks");
				}
				if(selectedItem.getText().equals("Add Author")){
					navigator.navigateTo("addAuthor");
				}
				if(selectedItem.getText().equals("List Authors")){
					navigator.navigateTo("listAuthors");
				}
				Notification.show("Selected Menu " +selectedItem.getText());
			}
		};
		
		menuLayout.addComponent(barmenu);
		// A menu with sub menu
		MenuItem mBooks = barmenu.addItem("Books", null, null);
		mBooks.addItem("Add Book", null, mycommand);
		mBooks.addItem("List Books", null, mycommand);
		// Another submenu item with a sub-submenu
		MenuItem mAuthors = barmenu.addItem("Authors", null, null);
		mAuthors.addItem("Add Author", null, mycommand);
		mAuthors.addItem("List Authors", null, mycommand);
		// Another submenu item with a sub-submenu
		MenuItem mPublishers = barmenu.addItem("Publishers", null, null);
		mPublishers.addItem("Add Publisher", null, mycommand);
		mPublishers.addItem("List Publishers", null, mycommand);
	}

}