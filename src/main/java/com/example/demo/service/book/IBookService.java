package com.example.demo.service.book;

import com.example.demo.model.dto.dtoRequest.BookRequest;
import com.example.demo.model.dto.dtoResponse.BookResponse;
import com.example.demo.model.entity.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface IBookService {
    Page<BookResponse> findAll(Pageable pageable);
    BookResponse save (BookRequest book);
    BookResponse remove(Long id);
    BookResponse findById(Long id);
}
