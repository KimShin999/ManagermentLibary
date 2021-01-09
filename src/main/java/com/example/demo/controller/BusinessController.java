package com.example.demo.controller;
import com.example.demo.model.ResponseData;
import com.example.demo.model.entity.Book;
import com.example.demo.model.entity.Student;
import com.example.demo.service.ConvertDTO;
import com.example.demo.service.book.IBookService;
import com.example.demo.service.student.IStudentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.demo.model.dto.dtoResponse.BookResponse;
import com.example.demo.model.dto.dtoResponse.StudentResponse;
import java.time.Duration;
import java.time.LocalDateTime;
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
    @Autowired
    private IBookService iBookService;
    @Autowired
    private ConvertDTO convertDTO;

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
                bookService.saveBack(book);
                return responseData;
            }
        }
        responseData.setMessage("quyen sach hien chua dc muon");
        responseData.setStatus("done");
        return responseData;
    }

    @PostMapping("/borrow/{idStudent}/{idBook}")
    private ResponseData borrowBooks2(@PathVariable Long idStudent ,@PathVariable Long idBook){
        Student student = convertDTO.mapperDTO(studentService.findById(idStudent)) ;
        if (student == null){
            responseData.setMessage("student id: " +idStudent + " is not defind");
            responseData.setData(null);
            responseData.setStatus("false");
            return responseData;
        }
        if (student.getCheckLegit() == false){
            responseData.setMessage(student.getName() + "is not legit");
            responseData.setData(null);
            responseData.setStatus("false");
            return responseData;
        }
        Book book = convertDTO.mapperDTO(iBookService.findById(idBook));
        if (book == null){
            responseData.setMessage("book id: "+ idBook + " is not defind");
            responseData.setData(null);
            responseData.setStatus("false");
            return responseData;
        }
        if (book.getStatus() == "UnAvailable"){
            responseData.setMessage("book id: "+ idBook + " is UnAvailable");
            responseData.setData(null);
            responseData.setStatus("false");
            return responseData;
        }
        book.setStudent(student);
        iBookService.saveBookBorrow(book);
        student.getBooks().add(book);
        responseData.setData(studentService.saveStudentBorrow(student));
        responseData.setMessage("success");
        return responseData;
    }

}
