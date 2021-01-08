package com.example.demo.model.dto.dtoResponse;

import com.example.demo.model.entity.Book;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class StudentResponse {
    @JsonProperty("id")
    private Long id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("email")
    private String email;
    @JsonProperty("numberPhone")
    private String numberPhone;
    @JsonProperty("books")
    private List<Book> books;
    @JsonProperty("checkLegit")
    private Boolean checkLegit;

    public StudentResponse(Long id, String name, String email, String numberPhone, List<Book> books, Boolean checkLegit) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.numberPhone = numberPhone;
        this.books = books;
        this.checkLegit = checkLegit;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNumberPhone() {
        return numberPhone;
    }

    public void setNumberPhone(String numberPhone) {
        this.numberPhone = numberPhone;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public Boolean getCheckLegit() {
        return checkLegit;
    }

    public void setCheckLegit(Boolean checkLegit) {
        this.checkLegit = checkLegit;
    }

    public StudentResponse() {
    }
}
