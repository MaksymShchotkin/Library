package com.library.Library.Controller;


import com.library.Library.Entities.Book;
import com.library.Library.Entities.User;
import com.library.Library.Mapper.BookMapper;
import com.library.Library.Mapper.UserMapper;
import com.library.Library.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    private final UserService userService;
    private final UserMapper userMapper;
    private final BookMapper bookMapper;

    @Autowired
    public UserController(UserService userService, UserMapper userMapper, BookMapper bookMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
        this.bookMapper = bookMapper;
    }
    @GetMapping("/hello")
    public String runTask() {
        // Perform some task
        System.out.println("Task is running, seems like mapping works too. \nWelcome!");

        // Provide feedback
        return "Task is running in the terminal...";
    }
    @GetMapping("/take-a-book")
    public String takeBook(User user, Book book){
        userService.takeBook(userMapper.toDTO(user),bookMapper.toDTO(book));
        return "take-book";
    }
    @GetMapping("/return-book")
    public String returnBook(User user, Book book) {
        userService.returnBook(userMapper.toDTO(user), bookMapper.toDTO(book));
        return "book-return";
    }
}