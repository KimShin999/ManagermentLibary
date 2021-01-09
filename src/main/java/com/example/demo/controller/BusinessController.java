package com.example.demo.controller;

import com.example.demo.model.ResponseData;
import com.example.demo.model.dto.dtoRequest.BookRequest;
import com.example.demo.model.dto.dtoResponse.BookResponse;
import com.example.demo.model.dto.dtoResponse.StudentResponse;
import com.example.demo.model.entity.Book;
import com.example.demo.model.entity.Student;
import com.example.demo.service.book.IBookService;
import com.example.demo.service.student.IStudentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.Arrays;

@RestController
@RequestMapping("api/book")
public class BusinessController {

    @Autowired
    private ResponseData responseData;
    @Autowired
    private IStudentService studentService;
    @Autowired
    private IBookService bookService;
    @Autowired
    private ModelMapper modelMapper;

    @PutMapping("/back/{idStudent}/{idBook}")
    public ResponseData back(@PathVariable Long idStudent, @PathVariable Long idBook) {
        StudentResponse studentResponse = studentService.findById(idStudent);
        BookResponse bookResponse = bookService.findById(idBook);
        if (bookResponse != null && studentResponse != null){
                Student student = modelMapper.map(studentResponse, Student.class);
                Book book = modelMapper.map(bookResponse, Book.class);
            if (idStudent.equals(book.getStudent().getId())) {
                responseData.setMessage("done");
                responseData.setStatus("ok");
                responseData.setData(book);
                if (LocalDateTime.now().isAfter(book.getEndDay())) {
                    int day = (int) ((((Duration.between(book.getEndDay(), LocalDateTime.now()).getSeconds())/60)/60)/24);
                    responseData.setMessage("tra muon: " + day + "ngay");
                    responseData.setStatus("done ");
                    responseData.setData(book);
                }
                student.getBooks().removeIf(b -> b.getStudent().getId().equals(idStudent));
                System.out.println(Arrays.toString(student.getBooks().toArray()));
                book.setStudent(null);
                book.setStartDay(null);
                book.setEndDay(null);
                book.setStatus("dang trong thu vien");
//                allow here
//                bookService.saveBack(book);
                return responseData;
            }
        }
        responseData.setMessage("quyen sach hien chua dc muon");
        responseData.setStatus("done");
        return responseData;
    }

}
