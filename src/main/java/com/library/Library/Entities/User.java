package com.library.Library.Entities;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class User{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private List<Integer> takenBooks = new ArrayList<>();
    @ElementCollection
    public List<Integer> getTakenBooks() {
        return takenBooks;
    }

    public void setTakenBooks(List<Integer> takenBooks) {
        this.takenBooks = takenBooks;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
