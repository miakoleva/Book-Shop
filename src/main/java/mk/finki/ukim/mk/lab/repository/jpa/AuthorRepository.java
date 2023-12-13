package mk.finki.ukim.mk.lab.repository.jpa;
import mk.finki.ukim.mk.lab.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long>{
}
