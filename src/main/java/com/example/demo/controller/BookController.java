package com.example.demo.controller;
import com.example.demo.model.ResponseData;
import com.example.demo.model.dto.dtoRequest.BookRequestCreate;
import com.example.demo.model.dto.dtoResponse.BookResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;
import com.example.demo.service.book.IBookService;
import java.util.List;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api")
public class BookController {

    @Autowired
    IBookService iBookService;

    @Autowired
    ResponseData responseData;

    String available = "Available";

    String unAvailable = "UnAvailable";

    @PostMapping(value = "/createBook")
    public ResponseData createBook(@RequestBody BookRequestCreate bookRequest){
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

    @GetMapping(value = "/findAllBook")
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

    @GetMapping(value = "/findBookByName/{name}")
    public ResponseData findByName(@PathVariable String name){
        List<BookResponse> bookResponses = iBookService.findAllByNameBookContaining(name);
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

    @GetMapping(value = "/findBookAvailable")
    public ResponseData findBookAvailable(){
        List<BookResponse> bookResponses = iBookService.findAllByStatusAvailable(available);
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

    @GetMapping(value = "/findBookUnAvailable")
    public ResponseData findBookUnAvailable(){
        List<BookResponse> bookResponses = iBookService.findAllByStatusUnAvailable(unAvailable);
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
