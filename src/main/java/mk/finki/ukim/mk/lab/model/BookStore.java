package mk.finki.ukim.mk.lab.model;

import lombok.Data;

@Data
public class BookStore {

    private Long id;
    private String name;
    private String city;
    private String address;


    public BookStore(String name, String city, String address) {
        this.id = (long) (Math.random()*1000);
        this.name = name;
        this.city = city;
        this.address = address;
    }
}
