package mk.finki.ukim.mk.lab.repository;

import mk.finki.ukim.mk.lab.bootstrap.DataHolder;
import mk.finki.ukim.mk.lab.model.Author;
import mk.finki.ukim.mk.lab.model.Book;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BookRepository {
    public List<Book> findAll(){
        return DataHolder.books;
    }

    public Book findByIsbn(String isbn){
        return DataHolder.books.stream().filter(b -> b.getIsbn().equals(isbn)).findFirst().get();
    }

    public Author addAuthorToBook(Author author, Book book){
        book.getAuthors().add(author);
        return author;
    }
}
