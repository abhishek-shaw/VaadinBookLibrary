package com.example.booklibrary;

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

import org.apache.commons.beanutils.BeanUtils;

public class AuthorService implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4564062435180022079L;
	// Create dummy data by randomly combining first and last names
    static String[] authorName = { "Suzanne Collins",
    							 " JK Rowling",
    							 "Harper Lee",
    							 "Anna Quindlen" };
    private static AuthorService instance;

    public static AuthorService createDemoService() {
        if (instance == null) {

            final AuthorService authorService = new AuthorService();

            Random r = new Random(0);
            Calendar cal = Calendar.getInstance();
            for (int i = 0; i < 100; i++) {
                Author author = new Author();
                author.setAuthorName(authorName[r.nextInt(authorName.length)]);
                author.setAuthorEmail(authorName[r.nextInt(authorName.length)].toLowerCase().replace(" ", "_")+"@gmail.com");
                cal.set(1930 + r.nextInt(70),
                        r.nextInt(11), r.nextInt(28));
                author.setDateOfBirth((cal.getTime()));
                authorService.save(author);
            }
            instance = authorService;
        }

        return instance;
    }

    private HashMap<Long, Author> authors = new HashMap<>();
    private long nextId = 0;

    public synchronized List<Author> findAll(String stringFilter) {
        ArrayList<Author> arrayList = new ArrayList<Author>();
        for (Author author : authors.values()) {
            try {
                boolean passesFilter = (stringFilter == null || stringFilter.isEmpty())
                        || author.toString().toLowerCase()
                                .contains(stringFilter.toLowerCase());
                if (passesFilter) {
                    arrayList.add(author.clone());
                }
            } catch (CloneNotSupportedException ex) {
                Logger.getLogger(AuthorService.class.getName()).log(
                        Level.SEVERE, null, ex);
            }
        }
        Collections.sort(arrayList, new Comparator<Author>() {

            @Override
            public int compare(Author o1, Author o2) {
                return (int) (o2.getId() - o1.getId());
            }
        });
        return arrayList;
    }

    public synchronized long count() {
        return authors.size();
    }

    public synchronized void delete(Book value) {
    	authors.remove(value.getId());
    }

    public synchronized void save(Author author) {
        if (author.getId() == null) {
            author.setId(nextId++);
        }
        try {
            author = (Author) BeanUtils.cloneBean(author);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
        authors.put(author.getId(), author);
    }

}
