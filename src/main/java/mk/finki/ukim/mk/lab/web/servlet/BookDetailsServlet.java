//package mk.finki.ukim.mk.lab.web.servlet;
//
//import jakarta.servlet.ServletException;
//import jakarta.servlet.annotation.WebServlet;
//import jakarta.servlet.http.HttpServlet;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import mk.finki.ukim.mk.lab.model.Book;
//import mk.finki.ukim.mk.lab.service.BookService;
//import org.thymeleaf.context.WebContext;
//import org.thymeleaf.spring6.SpringTemplateEngine;
//import org.thymeleaf.web.IWebExchange;
//import org.thymeleaf.web.servlet.JakartaServletWebApplication;
//
//import java.io.IOException;
//
//@WebServlet(urlPatterns = "/bookDetails")
//public class BookDetailsServlet extends HttpServlet {
//    private final BookService bookService;
//    private final SpringTemplateEngine templateEngine;
//
//
//    public BookDetailsServlet(BookService bookService, SpringTemplateEngine templateEngine) {
//        this.bookService = bookService;
//        this.templateEngine = templateEngine;
//    }
//
//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        IWebExchange webExchange = JakartaServletWebApplication
//                .buildApplication(getServletContext())
//                .buildExchange(req, resp);
//        WebContext webContext = new WebContext(webExchange);
//
//        String isbn = (String) req.getSession().getAttribute("isbn");
//        Book book = bookService.findBookByIsbn(isbn);
//
//       webContext.setVariable("book", book);
//        this.templateEngine.process(
//                "bookDetails.html",
//                webContext,
//                resp.getWriter()
//        );
//    }
//
//    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        super.doPost(req, resp);
//    }
//
//
//}
