package com.example.demo.service;
import com.example.demo.model.dto.dtoResponse.BookResponse;
import com.example.demo.model.dto.dtoResponse.StudentResponse;
import com.example.demo.model.entity.Book;
import com.example.demo.model.entity.Student;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ConvertDTO {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private BookResponse bookResponse;

    @Autowired
    private Student studentEntity;

    @Autowired
    private Book bookEntity;

    public BookResponse mapperDTO(Book book){
        if (book == null){
            return null;
        }
       return bookResponse = modelMapper.map(book, BookResponse.class);
    }

    public Book mapperDTO(BookResponse response){
        if (response == null){
            return null;
        }
        return modelMapper.map(response, Book.class);
    }

    public Student mapperDTO(StudentResponse studentResponse){
        if (studentResponse == null){
            return null;
        }
        return  modelMapper.map(studentResponse, Student.class);
    }
}
