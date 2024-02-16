package mk.finki.ukim.mk.lab.service;

import mk.finki.ukim.mk.lab.model.Book;
import mk.finki.ukim.mk.lab.model.Review;

import java.time.LocalDateTime;
import java.util.List;

public interface ReviewService {

    List<Review> listAllReviews(Book book);
    Double getAvgRatingForBook(Book book);

    List<Integer> getAllRatingsForBook(Book book);

    Review save(Integer score, LocalDateTime date, Long bookId);

    List<LocalDateTime> dateTimes(Book book);
    List<Review> getAllReviewsBetweenDates(Book book, LocalDateTime date1, LocalDateTime date2);

    Double getAvgRatingForAllBooks();

    Double getMaxScoreForAllBooks();

}
