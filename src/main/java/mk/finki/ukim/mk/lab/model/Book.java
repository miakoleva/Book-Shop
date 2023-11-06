package mk.finki.ukim.mk.lab.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class Book {

    private String isbn;
    private String title;
    private String genre;
    private int year;
    private List<Author> authors;
}
