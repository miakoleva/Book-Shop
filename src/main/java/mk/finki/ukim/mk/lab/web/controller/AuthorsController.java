package mk.finki.ukim.mk.lab.web.controller;

import mk.finki.ukim.mk.lab.model.Author;
import mk.finki.ukim.mk.lab.model.Book;
import mk.finki.ukim.mk.lab.service.AuthorService;
import mk.finki.ukim.mk.lab.service.BookService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/authors")
public class AuthorsController {

    private AuthorService authorService;
    private BookService bookService;

    public AuthorsController(AuthorService authorService, BookService bookService) {
        this.authorService = authorService;
        this.bookService = bookService;
    }

    @GetMapping
    public String getPage(@RequestParam String bookId, Model model){
        Optional<Book> book = this.bookService.findBookById(Long.valueOf(bookId));
        List<Author> existingAuthors = this.bookService.findBookByIsbn(book.get().getIsbn()).getAuthors();
        List<Author> additionalAuthors = this.authorService.filterExistingAuthors(existingAuthors);
        model.addAttribute("authors", additionalAuthors);
        model.addAttribute("bookId", bookId);
        model.addAttribute("isbn", book.get().getIsbn());
        return "authorList";
    }

    @PostMapping("{bookId}/{id}")
    public String addAuthorToBook(@PathVariable Long bookId,@PathVariable Long id){

       Optional<Book> book = bookService.findBookById(bookId);
        this.bookService.addAuthorToBook(id, book.get().getIsbn());

        return "redirect:/book-details?bookId=" + bookId;

    }

}
