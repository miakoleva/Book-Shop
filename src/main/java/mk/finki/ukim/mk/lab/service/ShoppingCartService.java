package mk.finki.ukim.mk.lab.service;

import mk.finki.ukim.mk.lab.model.Book;
import mk.finki.ukim.mk.lab.model.ShoppingCart;

import java.util.List;

public interface ShoppingCartService{

    List<Book> listAllBooksInShoppingCart(Long id);
    ShoppingCart getActiveShoppingCart(String username);
    ShoppingCart addBookToShoppingCart(String username, Long productId);

}
