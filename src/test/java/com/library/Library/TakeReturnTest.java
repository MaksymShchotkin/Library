package com.library.Library;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import com.library.Library.DTO.BookDTO;
import com.library.Library.DTO.UserDTO;
import com.library.Library.Entities.User;
import com.library.Library.Mapper.BookMapper;
import com.library.Library.Mapper.UserMapper;
import com.library.Library.Repository.BookRepository;
import com.library.Library.Repository.UserRepository;
import com.library.Library.Service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class TakeReturnTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserMapper userMapper;

    @Mock
    private BookRepository bookRepository;

    @Mock
    private BookMapper bookMapper;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testTakeBook() {
        // Prepare test data
        UserDTO userDTO = new UserDTO();
        userDTO.setId(1L); // Set user ID if required

        BookDTO bookDTO = new BookDTO();
        bookDTO.setStatus(true);
        bookDTO.setId(1);

        User user = new User(); // Create a User entity instance
        // You might need to populate the user entity with required data for the test

        // Mock the behavior of userMapper.toEntity(userDTO) to return a non-null User entity
        when(userMapper.toEntity(userDTO)).thenReturn(user);

        // Call the method under test
        userService.takeBook(userDTO, bookDTO);

        // Assert the behavior
        assertTrue(bookDTO.getStatus()); // Book status should be true after taking
        assertTrue(userDTO.getTakenBooks().contains(bookDTO.getId())); // User should have the book
        verify(userRepository, times(1)).save(any(User.class)); // UserRepository save method should be called once
        verify(bookRepository, times(1)).save(any()); // BookRepository save method should be called once
    }

    @Test
    public void testReturnBook() {
        // Create a UserDTO with some taken books
        UserDTO userDTO = new UserDTO();
        List<Integer> takenBooks = new ArrayList<>();
        takenBooks.add(1);
        userDTO.setTakenBooks(takenBooks);

        // Create a BookDTO with a status of false
        BookDTO bookDTO = new BookDTO();
        bookDTO.setStatus(false);
        bookDTO.setId(1);

        // Mock the behavior of userMapper.toEntity(userDTO) to return a non-null User entity
        User userEntity = new User(); // Create a non-null User entity
        when(userMapper.toEntity(userDTO)).thenReturn(userEntity);

        // Call the method under test
        userService.returnBook(userDTO, bookDTO);

        // Assertions
        assertTrue(bookDTO.getStatus()); // Book status should be true after returning
        assertFalse(userDTO.getTakenBooks().contains(bookDTO.getId())); // User should not have the book
        verify(userRepository, times(1)).save(any(User.class)); // UserRepository save method should be called once
        verify(bookRepository, times(1)).save(any()); // BookRepository save method should be called once
    }

    @Test
    public void testGetUsersWithTakenBooks() {
        // Mock data
        List<User> users = new ArrayList<>();
        User user1 = new User();
        user1.setId(1);
        users.add(user1);
        when(userRepository.findDistinctByTakenBooksIsNotNull()).thenReturn(users);

        // Call the method
        List<User> usersWithTakenBooks = userService.getUsersWithTakenBooks();

        // Assertions
        assertEquals(1, usersWithTakenBooks.size()); // There should be one user in the list
        assertEquals(1, usersWithTakenBooks.get(0).getId()); // The user ID should match
        verify(userRepository, times(1)).findDistinctByTakenBooksIsNotNull(); // UserRepository method should be called once
    }
}
