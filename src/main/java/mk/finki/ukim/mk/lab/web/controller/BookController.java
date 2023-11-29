package mk.finki.ukim.mk.lab.web.controller;

import mk.finki.ukim.mk.lab.model.Book;
import mk.finki.ukim.mk.lab.model.BookStore;
import mk.finki.ukim.mk.lab.service.BookService;
import mk.finki.ukim.mk.lab.service.BookStoreService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/books")
public class BookController {

    private BookService bookService;
    private BookStoreService bookStoreService;

    public BookController(BookService bookService, BookStoreService bookStoreService) {
        this.bookService = bookService;
        this.bookStoreService = bookStoreService;
    }


    @GetMapping
    public String getBooksPage(@RequestParam(required = false) String error, Model model){
        if (error != null && !error.isEmpty()) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
        }
        List<Book> books = this.bookService.listBooks();
        model.addAttribute("books", books);
        return "listBooks";

    }

    @PostMapping("{id}")
    public String addAuthor(@PathVariable Long id){
//        model.addAttribute("bookId", id);
        return "redirect:/authors?bookId=" + id;
    }

    @PostMapping("/delete/{id}")
    public String deleteBook(@PathVariable Long id){
        this.bookService.deleteBook(id);
        return "redirect:/books";
    }

    @GetMapping("/edit-form/{id}")
    public String editBook(@PathVariable(required = false) Long id, Model model){
       Book book = this.bookService.findBookById(id).get();
       List<BookStore> bookStores = this.bookStoreService.findAll();
       model.addAttribute("book", book);
       model.addAttribute("bookStores", bookStores);
       return "add-book";
    }

    @GetMapping("/add-book")
    public String editBook(Model model){
        List<BookStore> bookStores = this.bookStoreService.findAll();
        model.addAttribute("bookStores", bookStores);
        return "add-book";
    }

    @PostMapping("add-book")
    public String addBook(@RequestParam(required = false) Long id,
                          @RequestParam String title,
                          @RequestParam String isbn,
                          @RequestParam String genre,
                          @RequestParam int year,
                          @RequestParam Long bookStores){
        this.bookService.save(id, title, isbn, genre, year, bookStores);
        return "redirect:/books";
    }

    @PostMapping("clone/{id}")
    public String cloneBook(@PathVariable Long id){
        Book original = bookService.findBookById(id).get();
        this.bookService.save(null,"Copy of " +  original.getTitle(),
                original.getIsbn(), original.getGenre(),
                original.getYear(), original.getBookStore().getId());

        return "redirect:/books";
    }
}
