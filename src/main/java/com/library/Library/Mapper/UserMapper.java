package com.library.Library.Mapper;

import com.library.Library.DTO.BookDTO;
import com.library.Library.DTO.UserDTO;
import com.library.Library.Entities.Book;
import com.library.Library.Entities.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public UserDTO toDTO(User user) {
        UserDTO dto = new UserDTO();
        dto.setId(user.getId());
        dto.setName(user.getName());
        dto.setTakenBooks(user.getTakenBooks());
        return dto;
    }
    public User toEntity(UserDTO dto) {
        User user = new User();
        user.setId(dto.getId());
        user.setName(dto.getName());
        user.setTakenBooks(dto.getTakenBooks());
        return user;
    }
}
