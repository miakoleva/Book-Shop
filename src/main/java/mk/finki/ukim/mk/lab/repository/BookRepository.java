package mk.finki.ukim.mk.lab.repository;

import mk.finki.ukim.mk.lab.bootstrap.DataHolder;
import mk.finki.ukim.mk.lab.model.Author;
import mk.finki.ukim.mk.lab.model.Book;
import mk.finki.ukim.mk.lab.model.BookStore;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    public Optional<Book> findById(Long id){
        return DataHolder.books.stream().filter(b -> b.getId().equals(id)).findFirst();
    }

    public void deleteBook(Long id){
        Book book = DataHolder.books.stream()
                .filter(b -> b.getId().equals(id)).findFirst().get();

        DataHolder.books.remove(book);
    }

    public Optional<Book> save(Long id, String title, String isbn,
                               String genre, int year, BookStore bookStore){

        Optional<Book> book = findById(id);
        if(book.isPresent()){
        Book editedBook = new Book(isbn, title, genre, year, book.get().getAuthors(), bookStore);
        DataHolder.books.remove(book.get());
        DataHolder.books.add(editedBook);
        return Optional.of(editedBook);
        }else {
            Book newBook = new Book(isbn, title, genre, year, new ArrayList<>(), bookStore);
            DataHolder.books.add(newBook);
            return Optional.of(newBook);
        }
    }


}
