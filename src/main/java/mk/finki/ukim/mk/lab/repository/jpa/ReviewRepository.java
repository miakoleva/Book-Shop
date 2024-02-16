package mk.finki.ukim.mk.lab.repository.jpa;

import mk.finki.ukim.mk.lab.model.Book;
import mk.finki.ukim.mk.lab.model.Review;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {

    List<Review> findAllByBook(Book book);
    List<Review> findAllByTimestampAfterAndTimestampBefore(LocalDateTime date1, LocalDateTime date2);
    List<Review> findAllByBookAndTimestampAfterAndTimestampBefore(Book book, LocalDateTime date1, LocalDateTime date2);


//     List<Review> findAllByIdIn(List<Book> books);
//     List<Review> fi

    List<Review> findAllByIdIn(List<Long> bookIds);

}
