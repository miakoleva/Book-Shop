package mk.finki.ukim.mk.lab.repository;

import mk.finki.ukim.mk.lab.bootstrap.DataHolder;
import mk.finki.ukim.mk.lab.model.Author;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class AuthorRepository {

    public List<Author> findAll(){
        return DataHolder.authors;
    }

    public Optional<Author> findById(Long id){
        return DataHolder.authors.stream().filter(a -> a.getId().equals(id)).findFirst();
    }

}
