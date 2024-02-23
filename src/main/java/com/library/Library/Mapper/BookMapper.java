package com.library.Library.Mapper;

import com.library.Library.DTO.BookDTO;
import com.library.Library.Entities.Book;
import org.springframework.stereotype.Component;

@Component
public class BookMapper {
    public BookDTO toDTO(Book book){
        BookDTO dto = new BookDTO();
        dto.setId(book.getId());
        dto.setName(book.getName());
        dto.setAuthor(book.getAuthor());
        dto.setYear(book.getYear());
        dto.setStatus(book.getStatus());
        return dto;
    }
    public Book toEntity(BookDTO dto){
        Book book = new Book();
        book.setId(dto.getId());
        book.setName(dto.getName());
        book.setAuthor(dto.getAuthor());
        book.setYear(dto.getYear());
        book.setStatus(dto.getStatus());
        return book;
    }
}
