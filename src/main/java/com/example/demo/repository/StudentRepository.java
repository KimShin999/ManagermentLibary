package com.example.demo.repository;

import com.example.demo.model.entity.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface StudentRepository extends PagingAndSortingRepository<Student,Long> {
    List<Student> findAllByNameContaining(String name);
}
