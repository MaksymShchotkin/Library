package com.library.Library.Controller;

import com.library.Library.DTO.BookDTO;
import com.library.Library.Entities.Book;
import com.library.Library.Entities.User;
import com.library.Library.Service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class BookController {

    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }
    @GetMapping("/books")
    public String bookMain(){
        return "books-main";
    }

    @GetMapping("books/add-book")
    public String showAddBookForm(Model model) {
        model.addAttribute("book", new BookDTO());
        return "add-book"; // return the name of the HTML file (without extension)
    }

    @PostMapping("books/add-book")
    public String addBook(@ModelAttribute("book") BookDTO bookDTO) {
        bookService.addBook(bookDTO);
        return "redirect:/books"; // redirect to the book listing page
    }
    @GetMapping("/books/search")
    public String openSearch(Model model){
        model.addAttribute("book", new BookDTO());
        return "search-book";
    }
    @PostMapping("/books/search")
    public String searchBookByName(@ModelAttribute("book") BookDTO bookDTO) {
        bookDTO = bookService.findBookByName(bookDTO.getName());
        if (bookDTO.getName() != null) {
            return "search-results"; // Return the name of the Thymeleaf template to display search results
        } else {
            return "book-not-found"; // Return a view indicating that the book was not found
        }
    }
    @GetMapping("/books/search-results")
    public String showSearchResults() {
        return "search-results";
    }
    @GetMapping("/books/book-not-found")
    public String showBookNotFound() {
        return "book-not-found";
    }

}
