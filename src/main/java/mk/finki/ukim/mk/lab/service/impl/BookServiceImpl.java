package mk.finki.ukim.mk.lab.service.impl;

import mk.finki.ukim.mk.lab.model.Author;
import mk.finki.ukim.mk.lab.model.Book;
import mk.finki.ukim.mk.lab.model.BookStore;
import mk.finki.ukim.mk.lab.repository.AuthorRepository;
import mk.finki.ukim.mk.lab.repository.BookRepository;
import mk.finki.ukim.mk.lab.repository.BookStoreRepository;
import mk.finki.ukim.mk.lab.service.BookService;
import org.springframework.stereotype.Service;

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
    public Author addAuthorToBook(Long authorId, String isbn) {
        Author author = authorRepository.findById(authorId).get();
        Book book = bookRepository.findByIsbn(isbn);
        return bookRepository.addAuthorToBook(author, book);
    }

    @Override
    public Book findBookByIsbn(String isbn) {
        return bookRepository.findByIsbn(isbn);
    }

    @Override
    public Book deleteAuthorsForBook(String isbn) {
        Book book = bookRepository.findByIsbn(isbn);
        book.getAuthors().clear();
        return book;
    }

    @Override
    public Optional<Book> findBookById(Long id){
        return bookRepository.findById(id);
    }

    @Override
    public void deleteBook(Long id) {
        bookRepository.deleteBook(id);
    }

    @Override
    public Optional<Book> save(Long id, String title, String isbn, String genre, int year, Long bookStore) {
        BookStore bookStore1 = this.bookStoreRepository.findById(bookStore);
        return this.bookRepository.save(id, title, isbn, genre, year, bookStore1);
    }


}
