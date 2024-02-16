package mk.finki.ukim.mk.lab.service;

import mk.finki.ukim.mk.lab.model.Author;
import mk.finki.ukim.mk.lab.model.Book;
import mk.finki.ukim.mk.lab.model.BookStore;

import java.util.List;
import java.util.Optional;

public interface BookService{
    List<Book> listBooks();
    Author addAuthorToBook(Long authorId, Long bookId);
    Book findBookByIsbn(String isbn);
    Book deleteAuthorsForBook(String isbn);

    Optional<Book> findBookById(Long id);

    void deleteBookById(Long id);

    Optional<Book> save(Long id, String title, String isbn,
                        String genre, int year, List<Author> authors, Long bookStore);



}