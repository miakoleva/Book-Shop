//package mk.finki.ukim.mk.lab.web.servlet;
//
//import jakarta.servlet.ServletException;
//import jakarta.servlet.annotation.WebServlet;
//import jakarta.servlet.http.HttpServlet;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import mk.finki.ukim.mk.lab.model.Author;
//import mk.finki.ukim.mk.lab.service.AuthorService;
//import mk.finki.ukim.mk.lab.service.BookService;
//import org.thymeleaf.context.WebContext;
//import org.thymeleaf.spring6.SpringTemplateEngine;
//import org.thymeleaf.web.IWebExchange;
//import org.thymeleaf.web.servlet.JakartaServletWebApplication;
//
//import java.io.IOException;
//import java.util.List;
//import java.util.stream.Collectors;
//
//@WebServlet(urlPatterns = "/author")
//public class АuthorServlet extends HttpServlet {
//
//    private final AuthorService authorService;
//    private final SpringTemplateEngine templateEngine;
//    private final BookService bookService;
//
//    public АuthorServlet(AuthorService authorService, SpringTemplateEngine templateEngine, BookService bookService) {
//        this.authorService = authorService;
//        this.templateEngine = templateEngine;
//        this.bookService = bookService;
//    }
//
//
//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        IWebExchange webExchange = JakartaServletWebApplication
//                .buildApplication(getServletContext())
//                .buildExchange(req, resp);
//        WebContext webContext = new WebContext(webExchange);
//
//        String isbn = (String) req.getSession().getAttribute("isbn");
//
//        List<Author> existingAuthors = this.bookService.findBookByIsbn(isbn).getAuthors();
//        List<Author> additionalAuthors = this.authorService.filterExistingAuthors(existingAuthors);
//
//        webContext.setVariable("authors", additionalAuthors);
//        webContext.setVariable("isbn", isbn);
//        this.templateEngine.process(
//                "authorList.html",
//                webContext,
//                resp.getWriter()
//        );
//    }
//
//    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//
//        //avtoro i isbn
//        Long authorId = Long.valueOf(req.getParameter("authorId"));
//        String isbn = (String) req.getSession().getAttribute("isbn");
//        this.bookService.addAuthorToBook(authorId, isbn);
//
//        resp.sendRedirect("/bookDetails");
//
//    }
//}
