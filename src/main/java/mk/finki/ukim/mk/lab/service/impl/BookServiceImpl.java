package mk.finki.ukim.mk.lab.service.impl;

import mk.finki.ukim.mk.lab.model.Author;
import mk.finki.ukim.mk.lab.model.Book;
import mk.finki.ukim.mk.lab.model.BookStore;
import mk.finki.ukim.mk.lab.repository.jpa.AuthorRepository;
import mk.finki.ukim.mk.lab.repository.jpa.BookRepository;
import mk.finki.ukim.mk.lab.repository.jpa.BookStoreRepository;
import mk.finki.ukim.mk.lab.service.BookService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final BookStoreRepository bookStoreRepository;

    public BookServiceImpl(AuthorRepository authorRepository, BookRepository bookRepository, BookStoreRepository bookStoreRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.bookStoreRepository = bookStoreRepository;
    }

    @Override
    public List<Book> listBooks() {
        return bookRepository.findAll();
    }

    @Override
    public Author addAuthorToBook(Long authorId, Long bookId) {
        Author author = authorRepository.findById(authorId).get();
        Book book = bookRepository.findById(bookId).get();
        book.getAuthors().add(author);
        bookRepository.save(book);
        return author;
    }

    @Override
    public Book findBookByIsbn(String isbn) {
        return bookRepository.findByIsbn(isbn).get();
    }

    @Override
    public Book deleteAuthorsForBook(String isbn) {
        Book book = bookRepository.findByIsbn(isbn).get();
        book.getAuthors().clear();
        return book;
    }

    @Override
    public Optional<Book> findBookById(Long id){
        return bookRepository.findById(id);
    }

    @Override
    public void deleteById(Long id) {
        this.bookRepository.deleteBookById(id);
    }

    @Override
    public Optional<Book> save(Long id, String title, String isbn, String genre, int year, Long bookStore) {
        BookStore bookStore1 = this.bookStoreRepository.findById(bookStore).get();
        Book book = new Book(isbn, title,genre, year, new ArrayList<>(), bookStore1);
        return Optional.of(this.bookRepository.save(book));
    }


}
