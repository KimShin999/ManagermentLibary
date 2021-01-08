package com.example.demo.controller;
import com.example.demo.model.ResponseData;
import com.example.demo.model.dto.dtoRequest.BookRequest;
import com.example.demo.model.dto.dtoResponse.BookResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;
import com.example.demo.service.book.IBookService;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api")
public class BookController {

    @Autowired
    IBookService iBookService;

    @Autowired
    ResponseData responseData;

    @GetMapping("/createBook")
    public String createBook(){
        return "jaa";
    }

    @PostMapping(value = "/createBook")
    public ResponseData createBook(@RequestBody BookRequest bookRequest){
        BookResponse bookResponse = iBookService.save(bookRequest);
        if (bookResponse == null){
            responseData.setMessage("fail");
            responseData.setData(bookResponse);
            responseData.setStatus("NO");
        }
        responseData.setData(bookResponse);
        responseData.setMessage("success");
        responseData.setStatus("OK");
       return responseData;
    }

    @GetMapping(value = "/findAll")
    public ResponseData findAll(@PageableDefault(size = 3) Pageable pageable){
        Page<BookResponse> bookResponses = iBookService.findAll(pageable);
        if (bookResponses == null){
            responseData.setMessage("fail");
            responseData.setData(bookResponses);
            responseData.setStatus("NO");
        }
        responseData.setData(bookResponses);
        responseData.setMessage("success");
        responseData.setStatus("OK");
        return responseData;
    }

}
