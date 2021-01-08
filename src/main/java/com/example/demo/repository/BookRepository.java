package com.example.demo.repository;

import com.example.demo.model.entity.Book;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;


public interface BookRepository extends PagingAndSortingRepository<Book, Long> {
    List<Book> findAllByNameBookContaining(String name);
    List<Book> findAllByStatus(String Available);
}
