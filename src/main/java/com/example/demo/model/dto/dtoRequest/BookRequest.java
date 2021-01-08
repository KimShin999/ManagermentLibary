package com.example.demo.model.dto.dtoRequest;

import com.example.demo.model.entity.Student;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDateTime;

public class BookRequest {
    @JsonProperty("id")
    private Long id;

    @JsonProperty("nameBook")
    private String nameBook;

    @JsonProperty("description")
    private String description;

    @JsonProperty("student")
    private Student student;

    @JsonProperty("status")
    private String status;

    @JsonProperty("startDay")
    private LocalDateTime startDay;

    @JsonProperty("endDay")
    private LocalDateTime endDay;

    public BookRequest() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getStartDay() {
        return startDay;
    }

    public void setStartDay(LocalDateTime startDay) {
        this.startDay = startDay;
    }

    public LocalDateTime getEndDay() {
        return endDay;
    }

    public void setEndDay(LocalDateTime endDay) {
        this.endDay = endDay;
    }
}
