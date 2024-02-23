package com.library.Library.DTO;

import jakarta.persistence.ElementCollection;

import java.util.ArrayList;
import java.util.List;

public class UserDTO {
    private long id;
    private String name;
    private List<Integer> takenBooks = new ArrayList<>();

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
