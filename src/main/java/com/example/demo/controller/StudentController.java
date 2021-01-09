package com.example.demo.controller;
import com.example.demo.model.ResponseData;
import com.example.demo.model.dto.dtoRequest.StudentRequest;
import com.example.demo.model.dto.dtoResponse.StudentResponse;
import com.example.demo.model.entity.Student;
import com.example.demo.service.student.IStudentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api")
public class StudentController {
    @Autowired
    private IStudentService studentService;

    @Autowired
    private ResponseData responseData;

    @Autowired
    private ModelMapper modelMapper;


    @GetMapping("/getAll")
    public ResponseData getAll( @PageableDefault(size = 5) Pageable pageable){
        Page<StudentResponse> studentResponses = studentService.findAll(pageable);
        if (studentResponses.isEmpty()){
            responseData.setData(null);
            responseData.setMessage("not found any Element");
            responseData.setStatus("Not Ok");
            return responseData;
        }
        responseData.setStatus("ok");
        responseData.setMessage("show all");
        responseData.setData(studentResponses);
        return responseData;
    }
    @PostMapping("/save")
    public ResponseData save(@RequestBody StudentRequest studentRequest){

        try{
            StudentResponse studentResponse = studentService.save(studentRequest);
            responseData.setData(studentResponse);
            responseData.setMessage("save completed ");
            responseData.setStatus("ok");
        }catch (Exception e){
           responseData.setStatus("fail");
           responseData.setData(null);
           responseData.setMessage("fail");
        }
        return responseData;
    }
    @GetMapping("/search/{name}")
    public ResponseData searchByName(@PathVariable String name){
        List<StudentResponse> studentResponse = studentService.searchByName(name);
        if (studentResponse.isEmpty()){
            responseData.setStatus("Success");
            responseData.setData(null);
            responseData.setMessage("not found");
            return responseData;
        }
        responseData.setStatus("Success");
        responseData.setData(studentResponse);
        responseData.setMessage("ok");
        return responseData;
    }
    @DeleteMapping("/delete/{id}")
    public ResponseData deleteById(@PathVariable Long id){
            StudentResponse studentResponse = studentService.removeById(id);
            if (studentResponse==null){
                responseData.setStatus("Fail");
                responseData.setMessage("Not found");
                responseData.setData(null);
                return responseData;
            }
            responseData.setStatus("Success");
            responseData.setMessage("ok");
            responseData.setData(studentResponse);
            return responseData;
    }
    @PutMapping("/changeLegit/{id}")
    public ResponseData changeLegit(@PathVariable Long id){
        StudentResponse studentResponse = studentService.findById(id);
            if(studentResponse != null){
            Student student = modelMapper.map(studentResponse,Student.class);
            if (student.getCheckLegit() != null){
                if (student.getCheckLegit()){
                    student.setCheckLegit(false);
                    StudentRequest studentRequest = modelMapper.map(student,StudentRequest.class);
                    studentService.save(studentRequest);
                    responseData.setData(student);
                    responseData.setMessage("Ok");
                    responseData.setStatus("Done");
                    return responseData;
                }
                student.setCheckLegit(true);
                StudentRequest studentRequest = modelMapper.map(student,StudentRequest.class);
                studentService.save(studentRequest);
                responseData.setData(student);
                responseData.setMessage("Ok");
                responseData.setStatus("Done");
                return responseData;
            }
        }
        responseData.setData(null);
        responseData.setStatus("Fail");
        responseData.setMessage("Done");
        return responseData;
    }
}
