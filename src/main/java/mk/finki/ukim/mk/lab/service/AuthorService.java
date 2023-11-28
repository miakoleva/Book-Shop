package mk.finki.ukim.mk.lab.service;

import mk.finki.ukim.mk.lab.model.Author;

import java.util.List;

public interface AuthorService{
    List<Author> listAuthors();
    Author findById(Long id);

    List<Author> filterExistingAuthors(List<Author> existing);

}