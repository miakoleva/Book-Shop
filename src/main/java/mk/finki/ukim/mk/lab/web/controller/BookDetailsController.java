package mk.finki.ukim.mk.lab.web.controller;

import mk.finki.ukim.mk.lab.model.Book;
import mk.finki.ukim.mk.lab.service.BookService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/book-details")
public class BookDetailsController {

    BookService bookService;

    public BookDetailsController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping()
    public String getDetails(@RequestParam String bookId, Model model){
        Book book = bookService.findBookById(Long.valueOf(bookId)).get();
        model.addAttribute("book", book);
        return "bookDetails";
    }


}
