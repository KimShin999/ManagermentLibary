package com.example.demo.service.book;
import com.example.demo.model.dto.dtoRequest.BookRequestCreate;
import com.example.demo.model.dto.dtoResponse.BookResponse;
import com.example.demo.model.entity.Book;
import com.example.demo.repository.BookRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
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
    public List<BookResponse> findAllByNameBookContaining(String name) {
        List<Book> books = bookRepository.findAllByNameBookContaining(name);
        List<BookResponse> bookResponses =new ArrayList<>();
        for ( Book book: books){
            bookResponse = modelMapper.map(book, BookResponse.class);
            bookResponses.add(bookResponse);
        }
        return bookResponses;
    }

    @Override
    public List<BookResponse> findAllByStatusAvailable(String availabe) {
        List<Book> books = bookRepository.findAllByStatus(availabe);
        List<BookResponse> bookResponses =new ArrayList<>();
        for ( Book book: books){
            bookResponse = modelMapper.map(book, BookResponse.class);
            bookResponses.add(bookResponse);
        }
        return bookResponses;
    }

    @Override
    public List<BookResponse> findAllByStatusUnAvailable(String unAvailable) {
        List<Book> books = bookRepository.findAllByStatus(unAvailable);
        List<BookResponse> bookResponses =new ArrayList<>();
        for ( Book book: books){
            bookResponse = modelMapper.map(book, BookResponse.class);
            bookResponses.add(bookResponse);
        }
        return bookResponses;
    }

    @Override
    public BookResponse save(BookRequestCreate book) {
        bookEntity = modelMapper.map(book, Book.class);
        bookEntity.setStatus("Available");
        bookResponse = modelMapper.map(bookRepository.save(bookEntity),BookResponse.class);
        return bookResponse;
    }

    @Override
    public BookResponse saveBack(Book book) {
        BookResponse bookResponse = modelMapper.map(book,BookResponse.class);
        bookRepository.save(book);
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
