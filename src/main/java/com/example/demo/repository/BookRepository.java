package com.example.demo.repository;

import com.example.demo.model.entity.Book;
import org.springframework.data.repository.PagingAndSortingRepository;


public interface BookRepository extends PagingAndSortingRepository<Book, Long> {
}
