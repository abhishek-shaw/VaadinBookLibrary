package com.example.booklibrary;

import org.apache.commons.beanutils.BeanUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BookService implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 2840651329111865302L;
	// Create dummy data by randomly combining first and last names
    static String[] bookName = { "The Hunger Games",
    							 "Harry Potter and the Order of the Phoenix",
    							 "To Kill a Mockingbird",
    							 "Pride and Prejudice" };
    static String[] bookDescription = { "Winning will make you famous. Losing means certain death.", 
    									"Harry Potter is due to start his fifth year at Hogwarts School of Witchcraft and Wizardry.",
    									"The unforgettable novel of a childhood in a sleepy Southern town and the crisis of conscience that rocked it.",
    									"It is a truth universally acknowledged, that a single man in possession of a good fortune must be in want of a wife" };

    private static BookService instance;

    public static BookService createDemoService() {
        if (instance == null) {

            final BookService bookService = new BookService();

            Random r = new Random(0);
            Calendar cal = Calendar.getInstance();
            for (int i = 0; i < 100; i++) {
                Book book = new Book();
                book.setBookName(bookName[r.nextInt(bookName.length)]);
                book.setBookDescription(bookDescription[r.nextInt(bookDescription.length)]);
                book.setBookISBN("IS"+ String.valueOf(r.nextInt(10000)));
                book.setPrice(Double.valueOf(100 + r.nextInt(900)));
                cal.set(1930 + r.nextInt(70),
                        r.nextInt(11), r.nextInt(28));
                book.setPublishDate((cal.getTime()));
                bookService.save(book);
            }
            instance = bookService;
        }

        return instance;
    }

    private HashMap<Long, Book> books = new HashMap<>();
    private long nextId = 0;

    public synchronized List<Book> findAll(String stringFilter) {
        ArrayList<Book> arrayList = new ArrayList<Book>();
        for (Book book : books.values()) {
            try {
                boolean passesFilter = (stringFilter == null || stringFilter.isEmpty())
                        || book.toString().toLowerCase()
                                .contains(stringFilter.toLowerCase());
                if (passesFilter) {
                    arrayList.add(book.clone());
                }
            } catch (CloneNotSupportedException ex) {
                Logger.getLogger(BookService.class.getName()).log(
                        Level.SEVERE, null, ex);
            }
        }
        Collections.sort(arrayList, new Comparator<Book>() {

            @Override
            public int compare(Book o1, Book o2) {
                return (int) (o2.getId() - o1.getId());
            }
        });
        return arrayList;
    }

    public synchronized long count() {
        return books.size();
    }

    public synchronized void delete(Book value) {
        books.remove(value.getId());
    }

    public synchronized void save(Book entry) {
        if (entry.getId() == null) {
            entry.setId(nextId++);
        }
        try {
            entry = (Book) BeanUtils.cloneBean(entry);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
        books.put(entry.getId(), entry);
    }

}
