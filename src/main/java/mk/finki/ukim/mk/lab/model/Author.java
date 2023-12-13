package mk.finki.ukim.mk.lab.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import mk.finki.ukim.mk.lab.conventors.AuthorFullnameConverter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "authors")
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String surname;
    private String biography;
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private LocalDate dateOfBirth;

    @Convert(converter = AuthorFullnameConverter.class)
    private AuthorFullname fullname;

    public Author(){

    }

    public Author(String name, String surname, String biography, LocalDate dateOfBirth) {
        this.name = name;
        this.surname = surname;
        this.biography = biography;
        this.dateOfBirth = dateOfBirth;
    }



}
