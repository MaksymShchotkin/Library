package com.library.Library.Service;

import com.library.Library.DTO.BookDTO;
import com.library.Library.DTO.UserDTO;
import com.library.Library.Entities.User;
import com.library.Library.Mapper.BookMapper;
import com.library.Library.Mapper.UserMapper;
import com.library.Library.Repository.BookRepository;
import com.library.Library.Repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@SpringBootApplication
@ComponentScan(basePackages = {"com.library.Library"})
public class UserService {

    public final UserRepository userRepository;
    final UserMapper userMapper; // Ensure this dependency is properly injected
    public final BookRepository bookRepository;
    final BookMapper bookMapper;

    public UserService(UserRepository userRepository, UserMapper userMapper,
                       BookRepository bookRepository, BookMapper bookMapper){
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.bookRepository = bookRepository;
        this.bookMapper = bookMapper;
    }
    //save or update existing user
    public UserDTO saveUser(UserDTO userDTO) {
        User user = userMapper.toEntity(userDTO);
        user = userRepository.save(user);
        return userMapper.toDTO(user);
    }
    //check if user exists -> delete it
    public void deleteUser(Long userId) {
        User existingUser = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        userRepository.delete(existingUser);
    }
    public UserDTO getUserById(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        return userMapper.toDTO(user);
    }
    public void takeBook(UserDTO userDTO, BookDTO bookDTO){
        if(bookDTO.getStatus()!=false && userDTO.getTakenBooks()!=null){
            List<Integer> redList = new ArrayList<>(); // Initialize redList to an empty ArrayList
            redList.addAll(userDTO.getTakenBooks());
            redList.add(bookDTO.getId());
            bookDTO.setStatus(true); // true means TAKEN
            userDTO.setTakenBooks(redList);
            userRepository.save(userMapper.toEntity(userDTO));
            bookRepository.save(bookMapper.toEntity(bookDTO));
        } else {
            System.out.println("This book is taken");
        }
    }

    public void returnBook(UserDTO userDTO, BookDTO bookDTO){
        List<Integer> redList = userDTO.getTakenBooks();
        if(redList != null && redList.contains(bookDTO.getId())){ // Check if redList is not null
            redList.remove(Integer.valueOf(bookDTO.getId())); // Use Integer.valueOf to remove the object by value
            bookDTO.setStatus(true);
            userDTO.setTakenBooks(redList);
            userRepository.save(userMapper.toEntity(userDTO));
            bookRepository.save(bookMapper.toEntity(bookDTO));
        } else{
            System.out.println("This book does not belong to this user");
        }
    }

    public List<User> getUsersWithTakenBooks() {
        return userRepository.findDistinctByTakenBooksIsNotNull();
    }

}