package com.library.Library.Controller;

import com.library.Library.Entities.User;
import com.library.Library.Service.BookService;
import com.library.Library.Service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api") // Base path for all mappings in this controller
public class HelloController {
    private final UserService userService;
    private final BookService bookService;

    public HelloController(UserService userService, BookService bookService) {
        this.userService = userService;
        this.bookService = bookService;
    }

    @GetMapping("/hello")
    public String hello() {
        return "Hello, world!";
    }
    
    @GetMapping("/show-all-taken-books")
    public String showAllTakenBooks(List<User> users){
        for (User user:users) {
            for (long id:
            user.getTakenBooks()){
                System.out.println("This book is named "+bookService.getBookById(id)+" and it's take by "+user.getName());
            }
        }
        return "taken-books";
    }
}
