package com.library.Library.Service;

import com.library.Library.DTO.BookDTO;
import com.library.Library.Entities.Book;
import com.library.Library.Mapper.BookMapper;
import com.library.Library.Repository.BookRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@SpringBootApplication
@ComponentScan(basePackages = {"com.library.Library"})
public class BookService {

    public final BookRepository bookRepository;
    private final BookMapper bookMapper;

    public BookService(BookRepository bookRepository, BookMapper bookMapper) {
        this.bookRepository = bookRepository;
        this.bookMapper = bookMapper;
    }

    //save or update existing book
    public BookDTO saveBook(BookDTO bookDTO) {
        Book book = bookMapper.toEntity(bookDTO);
        book = bookRepository.save(book);
        return bookMapper.toDTO(book);
    }

    //check if book exists -> delete it
    public void deleteBook(Long bookId) {
        Book existingBook = bookRepository.findById(bookId)
                .orElseThrow(() -> new EntityNotFoundException("Book not found"));
        bookRepository.delete(existingBook);
    }

    public BookDTO getBookById(Long bookId) {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
        return bookMapper.toDTO(book);

    }

    public void addBook(BookDTO bookDTO) {
        saveBook(bookDTO);
    }

    public BookDTO findBookByName(String name) {
        Book book = bookRepository.findByName(name);

        if (book!=null) {
            BookDTO bookDTO = bookMapper.toDTO(book);
            return bookDTO;
        } else {
            return null;
        }
    }
    public List<Book> getAllTakenBooks() {
        return bookRepository.findByStatusFalse();
    }
}