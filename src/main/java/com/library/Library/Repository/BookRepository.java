package com.library.Library.Repository;
import com.library.Library.Entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    Book findByName(String name);
    List<Book> findByStatusFalse();
}
