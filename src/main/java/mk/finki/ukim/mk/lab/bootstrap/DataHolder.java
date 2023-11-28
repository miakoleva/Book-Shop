package mk.finki.ukim.mk.lab.bootstrap;

import jakarta.annotation.PostConstruct;
import mk.finki.ukim.mk.lab.model.Author;
import mk.finki.ukim.mk.lab.model.Book;
import mk.finki.ukim.mk.lab.model.BookStore;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DataHolder {

    public static List<Author> authors = null;
    public static List<Book> books = null;

    public static List<BookStore> bookStores = null;

    @PostConstruct
    public void init() {
        authors = new ArrayList<>();
        books = new ArrayList<>();
        bookStores = new ArrayList<>();

        Author author1 = new Author(1L, "Mia", "Koleva", "mk");
        Author author2 = new Author(2L, "Oliver", "Aleksovski", "oa");
        Author author3 = new Author(3L, "Simona", "Koleva", "sk");
        Author author4 = new Author(4L, "Mila", "Mihajlova", "mm");
        Author author5 = new Author(5L, "Nikola", "Spasic", "ns");


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
