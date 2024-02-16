package mk.finki.ukim.mk.lab.web.controller;

import mk.finki.ukim.mk.lab.model.Book;
import mk.finki.ukim.mk.lab.model.Review;
import mk.finki.ukim.mk.lab.service.BookService;
import mk.finki.ukim.mk.lab.service.ReviewService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/reviews")
public class ReviewController {

    ReviewService reviewService;
    BookService bookService;

    public ReviewController(ReviewService reviewService, BookService bookService){
        this.reviewService = reviewService;
        this.bookService = bookService;
    }

    @GetMapping("/add-review/{id}")
    public String getReviewPage(@PathVariable Long id,  Model model){
        model.addAttribute("bookId", id);
        return "review";
    }

    @PostMapping("/submit-review")
    public String submitReview(@RequestParam Long id,
                               @RequestParam Integer review,
                               @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dateCreated){

        this.reviewService.save(review, dateCreated, id);
        return "redirect:/book-details?bookId=" + id;

    }

    @GetMapping("/between-dates")
    public String getRatingsBetweenDates(@RequestParam Long idForDate, @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dateCreatedAfter,
                                         @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dateCreatedBefore,
                                         Model model){
        Book book = this.bookService.findBookById(idForDate).get();

        List<Review> reviews = this.reviewService.getAllReviewsBetweenDates(book, dateCreatedAfter, dateCreatedBefore);

        model.addAttribute("reviews", reviews);
        model.addAttribute("dateAfter", dateCreatedAfter);
        model.addAttribute("dateBefore", dateCreatedBefore);
        model.addAttribute("bookName", book.getTitle());
        model.addAttribute("bookId", book.getId());
        return "reviewsBetweenDates";
    }

    @GetMapping("/avg-ratings")
    public String getAvgRatings(Model model){
        Double avg = this.reviewService.getAvgRatingForAllBooks();
        model.addAttribute("avg", avg);
        return "review";
    }

    @GetMapping("/max-avg")
    public String getMaxRating(Model model){
        Double max = this.reviewService.getMaxScoreForAllBooks();
        model.addAttribute("max", max);
        return "review";
    }







}
