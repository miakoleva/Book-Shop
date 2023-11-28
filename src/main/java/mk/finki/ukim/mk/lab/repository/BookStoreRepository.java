package mk.finki.ukim.mk.lab.repository;

import mk.finki.ukim.mk.lab.bootstrap.DataHolder;
import mk.finki.ukim.mk.lab.model.BookStore;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BookStoreRepository {

    public List<BookStore> findAll(){
        return DataHolder.bookStores;
    }

    public BookStore findById(Long id){
        return DataHolder.bookStores.stream()
                .filter(b->b.getId().equals(id)).findFirst().get();
    }
}
