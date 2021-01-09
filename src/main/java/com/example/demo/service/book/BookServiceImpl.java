package com.example.demo.service.book;
import com.example.demo.model.dto.dtoRequest.BookRequestCreate;
import com.example.demo.model.dto.dtoResponse.BookResponse;
import com.example.demo.model.entity.Book;
import com.example.demo.model.entity.Student;
import com.example.demo.repository.BookRepository;
import com.example.demo.repository.StudentRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Service
public class BookServiceImpl implements IBookService{

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private StudentRepository studentRepository;

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
        if (books.isEmpty()){
            return null;
        }
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
        if (books.isEmpty()){
            return null;
        }
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
        if (books.isEmpty()){
            return null;
        }
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
    public BookResponse saveBookBorrow(Book book){
        book.setStartDay(LocalDateTime.now());
        book.setEndDay(LocalDateTime.now().plusDays(5));
        book.setStatus("UnAvailable");
        bookResponse = modelMapper.map(bookRepository.save(book),BookResponse.class);
        return bookResponse;
    }

    @Override
    public BookResponse remove(Long id) {
        Optional<Book> book = bookRepository.findById(id);
        if (book.isPresent()){
            bookResponse = modelMapper.map(bookEntity, BookResponse.class);
            bookRepository.deleteById(id);
            return bookResponse;
        }
        return null;
    }

    @Override
    public BookResponse findById(Long id) {
        Optional<Book> book = bookRepository.findById(id);
        if (book.isPresent()){
            bookResponse = modelMapper.map(bookEntity, BookResponse.class);
            return bookResponse;
        }
        return null;
    }

    @Override
    public Student borrow(Long idStu, Long idBook){
        Student student = studentRepository.findById(idStu).get();
        if (student == null){
            return null;
        }
        if (student.getCheckLegit() == false || student.getCheckLegit() == null){
            return null;
        }
        Book book = bookRepository.findById(idBook).get();
        if (book == null){
            return null;
        }
        if (book.getStatus().equals("UnAvailable")){
            return null;
        }
        book.setStudent(student);
        book.setStartDay(LocalDateTime.now());
        book.setEndDay(LocalDateTime.now().plusDays(5));
        book.setStatus("UnAvailable");
        bookRepository.save(book);
        student.getBooks().add(book);
        return studentRepository.save(student);
    }

}
