package com.example.demo.service.book;

import com.example.demo.model.dto.dtoRequest.BookRequest;
import com.example.demo.model.dto.dtoRequest.BookRequestCreate;
import com.example.demo.model.dto.dtoResponse.BookResponse;
import com.example.demo.model.entity.Book;
import com.example.demo.model.entity.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;


public interface IBookService {
    Page<BookResponse> findAll(Pageable pageable);
    BookResponse save (BookRequestCreate book);
    BookResponse saveBookBorrow(Book book);
    BookResponse remove(Long id);
    BookResponse findById(Long id);
    List<BookResponse> findAllByNameBookContaining(String name);
    List<BookResponse> findAllByStatusAvailable(String available);
    List<BookResponse> findAllByStatusUnAvailable(String unAvailable);
    Student borrow(Long idStu, Long idBook);
}
