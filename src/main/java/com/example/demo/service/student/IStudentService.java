package com.example.demo.service.student;
import com.example.demo.model.dto.dtoRequest.StudentRequest;
import com.example.demo.model.dto.dtoResponse.StudentResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;

public interface IStudentService {
    Page<StudentResponse> findAll(Pageable pageable);
    StudentResponse findById(Long id);
    StudentResponse save(StudentRequest studentRequest);
    StudentResponse removeById(Long id);
    List<StudentResponse> searchByName(String name);
}
