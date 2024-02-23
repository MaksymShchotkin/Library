package com.library.Library;

import com.library.Library.DTO.UserDTO;
import com.library.Library.Service.UserService;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@Transactional
public class UserTest {
    @Autowired
    private UserService userService;


    // Autowire other dependencies like UserRepository if needed...

    @DisplayName("Testing creation of user DTO, saving it and reading it from DB.")
    @Test
    public void userWithSteps() {
        UserDTO userToSave = createUser();
        UserDTO savedUser = saveUserToDB(userToSave);
        UserDTO retrievedUser = getUserById(savedUser.getId());
        assertMatchingUsers(retrievedUser);

    }
    @DisplayName("Create a DTO user")
    public UserDTO createUser() {
        UserDTO userToSave = new UserDTO();
        userToSave.setName("Billy");
        userToSave.setTakenBooks(new ArrayList<>());
        return userToSave;
    }

    @DisplayName("Save the user")
    public UserDTO saveUserToDB(UserDTO userToSave){
        return userService.saveUser(userToSave);
    }

    @DisplayName("Get the user by ID from the DB.")
    public UserDTO getUserById(Long userId){
        return userService.getUserById(userId);
    }
    @DisplayName("Display that the saved user has correct parameters.")
    public void assertMatchingUsers(UserDTO retrievedUser){
        // Assert that the retrieved user matches the saved user
        assertEquals("Billy", retrievedUser.getName());
        assertEquals(new ArrayList<>(), retrievedUser.getTakenBooks());
    }
}
