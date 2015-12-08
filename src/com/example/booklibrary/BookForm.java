package com.example.booklibrary;

import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.event.ShortcutAction;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Button;
import com.vaadin.ui.DateField;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextField;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.themes.ValoTheme;


public class BookForm extends FormLayout implements View{

    /**
	 * 
	 */
	private static final long serialVersionUID = 558837724743698580L;
	Button save = new Button("Save", this::save);
    Button cancel = new Button("Cancel", this::cancel);
    TextField bookName = new TextField("Book Name");
    TextField bookDescription = new TextField("Book Description");
    TextField bookISBN = new TextField("Book ISBN");
    TextField price = new TextField("Price");
    DateField publishDate = new DateField("Publish Date");
   /* OptionGroup single = new OptionGroup("Gender");
	single.addItems("Male", "Female");*/
    
    BookService bookService;

    Book book;

    // Easily bind forms to beans and manage validation and buffering
    BeanFieldGroup<Book> formFieldBindings;

    public BookForm(BookService bookService) {
    	this.bookService = bookService;
        configureComponents();
        buildLayout();
    }

    private void configureComponents() {
        /* Highlight primary actions.
         *
         * With Vaadin built-in styles you can highlight the primary save button
         * and give it a keyboard shortcut for a better UX.
         */
        save.setStyleName(ValoTheme.BUTTON_PRIMARY);
        save.setClickShortcut(ShortcutAction.KeyCode.ENTER);
        setVisible(false);
    }

    private void buildLayout() {
        setSizeUndefined();
        setMargin(true);

        HorizontalLayout actions = new HorizontalLayout(save, cancel);
        actions.setSpacing(true);

		addComponents(actions, bookName, bookDescription, bookISBN, price, publishDate);
    }

    public void save(Button.ClickEvent event) {
        try {
            // Commit the fields from UI to DAO
            formFieldBindings.commit();

            // Save DAO to backend with direct synchronous service API
            //getUI().bookService.save(book);

            String msg = String.format("Saved book '%s by %s'.",
            		book.getBookName(),
            		book.getAuthor().getAuthorName());
            Notification.show(msg,Type.TRAY_NOTIFICATION);
            //getUI().refreshBooks();
        } catch (FieldGroup.CommitException e) {
            // Validation exceptions could be shown here
        }
    }

    public void cancel(Button.ClickEvent event) {
        // Place to call business logic.
        Notification.show("Cancelled", Type.TRAY_NOTIFICATION);
        //getUI().bookList.select(null);
    }

    void edit(Book book) {
        this.book = book;
        if(book != null) {
            // Bind the properties of the contact POJO to fiels in this form
            formFieldBindings = BeanFieldGroup.bindFieldsBuffered(book, this);
            bookName.focus();
        }
        setVisible(book != null);
    }

	@Override
	public void enter(ViewChangeEvent event) {
		// TODO Auto-generated method stub
		setVisible(true);
	}

}
