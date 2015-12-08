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

public class AuthorForm extends FormLayout implements View{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3343391751430453834L;
	Button save = new Button("Save", this::save);
    Button cancel = new Button("Cancel", this::cancel);
    TextField authorName = new TextField("Author Name");
    TextField authorEmail = new TextField("Author Email");
    DateField dateOfBirth = new DateField("Birth date");
    
    Author author;
    AuthorService authorService;

    // Easily bind forms to beans and manage validation and buffering
    BeanFieldGroup<Author> formFieldBindings;

    public AuthorForm(AuthorService authorService) {
    	this.authorService = authorService;
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

		addComponents(actions, authorName, authorEmail, dateOfBirth);
    }

    public void save(Button.ClickEvent event) {
        try {
            // Commit the fields from UI to DAO
            formFieldBindings.commit();

            // Save DAO to backend with direct synchronous service API
            getUI().authorService.save(author);

            String msg = String.format("Saved author '%s'.",
            		author.getAuthorName());
            Notification.show(msg,Type.TRAY_NOTIFICATION);
           // getUI().refreshAuthors();
        } catch (FieldGroup.CommitException e) {
            // Validation exceptions could be shown here
        }
    }

    public void cancel(Button.ClickEvent event) {
        // Place to call business logic.
        Notification.show("Cancelled", Type.TRAY_NOTIFICATION);
        //getUI().bookList.select(null);
    }

    void edit(Author author) {
        this.author = author;
        if(author != null) {
            // Bind the properties of the contact POJO to fiels in this form
            formFieldBindings = BeanFieldGroup.bindFieldsBuffered(author, this);
            authorName.focus();
        }
        setVisible(author != null);
    }

    @Override
    public BookExampleUI getUI() {
        return (BookExampleUI) super.getUI();
    }

	@Override
	public void enter(ViewChangeEvent event) {
		// TODO Auto-generated method stub
		setVisible(true);
	}

}
