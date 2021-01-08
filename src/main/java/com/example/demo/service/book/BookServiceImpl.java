package com.example.demo.service.book;

import com.example.demo.model.dto.dtoRequest.BookRequest;
import com.example.demo.model.dto.dtoResponse.BookResponse;
import com.example.demo.model.entity.Book;
import com.example.demo.repository.BookRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class BookServiceImpl implements IBookService{

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private BookResponse bookResponse;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private Book bookEntity;

    @Override
    public Page<BookResponse> findAll(Pageable pageable) {
        Page<Book> books = bookRepository.findAll(pageable);
        return books.map(new Function<Book, BookResponse>() {
            @Override
            public BookResponse apply(Book book) {
                return modelMapper.map(book, BookResponse.class);
            }
        });
    }

    @Override
    public BookResponse save(BookRequest book) {
        bookEntity = modelMapper.map(book, Book.class);
        bookResponse = modelMapper.map(bookRepository.save(bookEntity),BookResponse.class);
        return bookResponse;
    }

    @Override
    public BookResponse remove(Long id) {
        bookEntity = bookRepository.findById(id).get();
        bookResponse = modelMapper.map(bookEntity, BookResponse.class);
        bookRepository.deleteById(id);
        return bookResponse;
    }

    @Override
    public BookResponse findById(Long id) {
        bookEntity = bookRepository.findById(id).get();
        bookResponse = modelMapper.map(bookEntity, BookResponse.class);
        return bookResponse;
    }

}
