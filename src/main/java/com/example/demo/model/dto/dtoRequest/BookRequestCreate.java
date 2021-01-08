package com.example.demo.model.dto.dtoRequest;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BookRequestCreate {
    @JsonProperty("nameBook")
    private String nameBook;

    @JsonProperty("description")
    private String description;

    public BookRequestCreate(String nameBook, String description) {
        this.nameBook = nameBook;
        this.description = description;
    }

    public BookRequestCreate() {
    }

    public String getNameBook() {
        return nameBook;
    }

    public void setNameBook(String nameBook) {
        this.nameBook = nameBook;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
