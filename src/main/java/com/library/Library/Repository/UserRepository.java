package com.library.Library.Repository;

import com.library.Library.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findDistinctByTakenBooksIsNotNull();

}