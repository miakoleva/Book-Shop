package mk.finki.ukim.mk.lab.bootstrap;

import jakarta.annotation.PostConstruct;
import mk.finki.ukim.mk.lab.model.Author;
import mk.finki.ukim.mk.lab.model.Book;
import mk.finki.ukim.mk.lab.model.BookStore;
import mk.finki.ukim.mk.lab.model.Review;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class DataHolder {

    public static List<Author> authors = null;
    public static List<Book> books = null;

    public static List<BookStore> bookStores = null;
    public static List<Review> reviews = null;

    @PostConstruct
    public void init() {
        authors = new ArrayList<>();
        books = new ArrayList<>();
        bookStores = new ArrayList<>();
        reviews = new ArrayList<>();

        Author author1 = new Author("Mia", "Koleva", "mk", LocalDate.of(2002,12,3));
        Author author2 = new Author( "Oliver", "Aleksovski", "oa", LocalDate.of(2000,10,25));
        Author author3 = new Author("Simona", "Koleva", "sk", LocalDate.of(1995,9,24));
        Author author4 = new Author( "Mila", "Mihajlova", "mm", LocalDate.of(1998,11,15));
        Author author5 = new Author( "Nikola", "Spasic", "ns", LocalDate.of(1975, 5, 5));


        authors.add(author1);
        authors.add(author2);
        authors.add(author3);
        authors.add(author4);
        authors.add(author5);

        bookStores.add(new BookStore("Knizara1", "Skopje", "111"));
        bookStores.add(new BookStore("Knizara2" ,"Kumanovo", "222"));
        bookStores.add(new BookStore("Knizara3" ,"Kratovo", "333"));
        bookStores.add(new BookStore("Knizara4" ,"Stip", "444"));
        bookStores.add(new BookStore("Knizara5" ,"Skopje", "555"));

        books.add(new Book("aaa", "Book1", "thriller", 2004, new ArrayList<Author>(), bookStores.get(0)));
        books.add(new Book("bbb", "Book2", "romance", 2008, new ArrayList<Author>(), bookStores.get(1)));
        books.add(new Book("ccc", "Book3", "thriller", 2010, new ArrayList<Author>(), bookStores.get(2)));
        books.add(new Book("ddd", "Book4", "horror", 2006, new ArrayList<Author>(), bookStores.get(3)));
        books.add(new Book("eee", "Book5", "western", 2000, new ArrayList<Author>(), bookStores.get(4)));


    }
}
