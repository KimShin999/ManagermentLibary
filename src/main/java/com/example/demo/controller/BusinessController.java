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


@RestController
@RequestMapping("api/book")
public class BusinessController {

    @Autowired
    private ResponseData responseData;

    @Autowired
    private IStudentService studentService;

    @Autowired
    private IBookService iBookService;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ConvertDTO convertDTO;

//    @PutMapping("/borrow/{idStudent}/{idBook}")
//    private ResponseData borrowBooks(@PathVariable Long idStudent ,@PathVariable Long idBook){
//        responseData.setData( iBookService.borrow(idStudent, idBook));
//        responseData.setMessage("OK");
//        return responseData;
//    }

    @PostMapping("/borrow/{idStudent}/{idBook}")
    private ResponseData borrowBooks2(@PathVariable Long idStudent ,@PathVariable Long idBook){
        Student student = convertDTO.mapperDTO(studentService.findById(idStudent)) ;
        if (student == null){
            responseData.setMessage("student id: " +idStudent + " is not defind");
            return responseData;
        }
        if (student.getCheckLegit() == false){
            responseData.setMessage(student.getName() + "is not legit");
            return responseData;
        }
        Book book = modelMapper.map(iBookService.findById(idBook), Book.class);
        if (book == null){
            responseData.setMessage("book id: "+ idBook + " is not defind");
            return responseData;
        }
        if (book.getStatus() == "UnAvailable"){
            responseData.setMessage("book id: "+ idBook + " is UnAvailable");
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
