package com.example.demo.model;

import org.springframework.stereotype.Component;

@Component
public class ResponseData {

    private String message;

    private Object data;

    private String status;

    public ResponseData() {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
