package com.example.demo.controller;
import com.example.demo.model.ResponseData;
import com.example.demo.model.dto.dtoRequest.StudentRequest;
import com.example.demo.model.dto.dtoResponse.StudentResponse;
import com.example.demo.service.student.IStudentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;


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
            responseData.setMessage("save completed "+ studentResponse);
            responseData.setStatus("ok");
        }catch (Exception e){
           responseData.setStatus("not ok");
           responseData.setData(null);
           responseData.setMessage("denied");
        }
        return responseData;
        //
    }
}
