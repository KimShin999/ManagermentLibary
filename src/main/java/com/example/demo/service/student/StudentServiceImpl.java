package com.example.demo.service.student;
import com.example.demo.model.dto.dtoRequest.StudentRequest;
import com.example.demo.model.dto.dtoResponse.StudentResponse;
import com.example.demo.model.entity.Student;
import com.example.demo.repository.StudentRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;


@Service
public class StudentServiceImpl implements com.example.demo.service.student.IStudentService {
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public Page<StudentResponse> findAll(Pageable pageable) {
        pageable = PageRequest.of(0,5 , Sort.by("name"));
        Page<Student> students= studentRepository.findAll(pageable);
        return students.map(new Function<Student, StudentResponse>() {
            @Override
            public StudentResponse apply(Student student) {
                return modelMapper.map(student,StudentResponse.class);
            }
        });
    }

    @Override
    public StudentResponse findById(Long id) {
        if (studentRepository.findById(id).isPresent()){
            Student student = studentRepository.findById(id).get();
            return modelMapper.map(student,StudentResponse.class);
        }
        return null;
    }

    @Override
    public StudentResponse save(StudentRequest studentRequest) {
        Student student = modelMapper.map(studentRequest,Student.class);
//        student.setCheckLegit(true);
        try{
            studentRepository.save(student);
            return modelMapper.map(student,StudentResponse.class);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public StudentResponse saveStudentBorrow(Student student) {
       StudentResponse studentResponse = modelMapper.map(studentRepository.save(student), StudentResponse.class);
        return studentResponse;
    }

    @Override
    public StudentResponse removeById(Long id) {
      try {
          Student student = studentRepository.findById(id).get();
          StudentResponse studentResponse = modelMapper.map(student,StudentResponse.class);
          studentRepository.deleteById(id);
          return studentResponse;
      }catch (Exception ignored){
          return null;
      }

    }

    @Override
    public List<StudentResponse> searchByName(String name) {
        List<Student> students = studentRepository.findAllByNameContaining(name);
        List<StudentResponse> studentResponses = new ArrayList<>();
        for (Student student: students) {
            StudentResponse studentResponse = modelMapper.map(student,StudentResponse.class);
            studentResponses.add(studentResponse);
        }
        return studentResponses;
    }


}
