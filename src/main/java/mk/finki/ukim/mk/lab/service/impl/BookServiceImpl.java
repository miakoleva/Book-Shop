package mk.finki.ukim.mk.lab.service.impl;

import jakarta.transaction.Transactional;
import mk.finki.ukim.mk.lab.model.Author;
import mk.finki.ukim.mk.lab.model.Book;
import mk.finki.ukim.mk.lab.model.BookStore;
import mk.finki.ukim.mk.lab.model.Review;
import mk.finki.ukim.mk.lab.repository.jpa.*;
import mk.finki.ukim.mk.lab.service.BookService;
import mk.finki.ukim.mk.lab.service.ShoppingCartService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final BookStoreRepository bookStoreRepository;
    private final ReviewRepository reviewRepository;
    private final ShoppingCartRepository shoppingCartRepository;



    public BookServiceImpl(AuthorRepository authorRepository, BookRepository bookRepository, BookStoreRepository bookStoreRepository
            , ReviewRepository reviewRepository, ShoppingCartRepository shoppingCartRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.bookStoreRepository = bookStoreRepository;
        this.reviewRepository = reviewRepository;
        this.shoppingCartRepository = shoppingCartRepository;

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
    public Optional<Book> findBookById(Long id) {
        return bookRepository.findById(id);
    }

    @Override
    public void deleteBookById(Long id) {

        Book book = this.bookRepository.findById(id).get();

        List<Long> reviewsIds = this.reviewRepository.findAllByBook(book).stream()
                .map(review -> review.getId()).collect(Collectors.toList());
        this.reviewRepository.deleteAllByIdInBatch(reviewsIds);

        List<Book> books = new ArrayList<>();
        books.add(book);
        List<Long> cartIds = this.shoppingCartRepository.findAllByBooksIn(books)
                .stream().map(i -> i.getId()).collect(Collectors.toList());

        this.shoppingCartRepository.deleteAllById(cartIds);
        this.bookRepository.deleteById(id);
    }

    @Override
    @Transactional
    public Optional<Book> save(Long id, String title, String isbn, String genre, int year,
                               List<Author> authors, Long bookStore) {
        BookStore bookStore1 = this.bookStoreRepository.findById(bookStore).get();

        if (id == null) {
            Book book = new Book(isbn, title, genre, year, new ArrayList<>(), bookStore1);
            book.getAuthors().addAll(authors);
            return Optional.of(this.bookRepository.save(book));
        } else {
            this.bookRepository.deleteById(id);

            Book book = new Book(isbn, title, genre, year, new ArrayList<>(), bookStore1);
            book.getAuthors().addAll(authors);
            return Optional.of(this.bookRepository.save(book));

        }

    }


}
