package mk.finki.ukim.mk.lab.service.impl;

import mk.finki.ukim.mk.lab.model.Book;
import mk.finki.ukim.mk.lab.model.Review;
import mk.finki.ukim.mk.lab.repository.jpa.BookRepository;
import mk.finki.ukim.mk.lab.repository.jpa.ReviewRepository;
import mk.finki.ukim.mk.lab.service.ReviewService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;
    private final BookRepository bookRepository;

    public ReviewServiceImpl(ReviewRepository reviewRepository, BookRepository bookRepository) {
        this.reviewRepository = reviewRepository;
        this.bookRepository = bookRepository;
    }

    @Override
    public List<Review> listAllReviews(Book book) {
        return this.reviewRepository.findAllByBook(book);
    }

    @Override
    public Double getAvgRatingForBook(Book book) {
        List<Review> reviews = listAllReviews(book);
        return reviews.stream()
                .mapToDouble(r -> r.getScore())
                .average().orElse(0.0);
    }

    @Override
    public List<Integer> getAllRatingsForBook(Book book) {
        List<Review> reviews = listAllReviews(book);
        return reviews.stream().map(r -> r.getScore()).collect(Collectors.toList());
    }

    @Override
    public Review save(Integer score, LocalDateTime date, Long bookId) {
        Book book = bookRepository.findById(bookId).get();
        Review review = new Review(score, null, book, date);
        return this.reviewRepository.save(review);

    }

    @Override
    public List<LocalDateTime> dateTimes(Book book) {
        List<Review> reviews = listAllReviews(book);
        return reviews.stream().map(d -> d.getTimestamp()).collect(Collectors.toList());
    }

    @Override
    public List<Review> getAllReviewsBetweenDates(Book book, LocalDateTime date1, LocalDateTime date2) {
        return reviewRepository.findAllByBookAndTimestampAfterAndTimestampBefore(book, date1, date2);
    }


}
