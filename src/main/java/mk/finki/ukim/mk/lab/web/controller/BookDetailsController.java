package mk.finki.ukim.mk.lab.web.controller;

import mk.finki.ukim.mk.lab.model.Book;
import mk.finki.ukim.mk.lab.model.Review;
import mk.finki.ukim.mk.lab.service.BookService;
import mk.finki.ukim.mk.lab.service.ReviewService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/book-details")
public class BookDetailsController {

    BookService bookService;
    ReviewService reviewService;

    public BookDetailsController(BookService bookService, ReviewService reviewService) {
        this.bookService = bookService;
        this.reviewService = reviewService;
    }

    @GetMapping()
    public String getDetails(@RequestParam String bookId, Model model){
        Book book = bookService.findBookById(Long.valueOf(bookId)).get();

        Double avg = reviewService.getAvgRatingForBook(book);
        List<Integer> reviews = reviewService.getAllRatingsForBook(book);

        List<LocalDateTime> dateTimes = reviewService.dateTimes(book);

        model.addAttribute("book", book);
        model.addAttribute("rating", avg);
        model.addAttribute("reviews", reviews);
        model.addAttribute("dateTimes", dateTimes);
        return "bookDetails";
    }


}
