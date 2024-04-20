package com.Cozastore_java.CozaStore_java.controller;

import com.Cozastore_java.CozaStore_java.payload.response.BaseResponse;
import com.Cozastore_java.CozaStore_java.service.imp.SizeServiceImp;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
@RequestMapping("/size")
public class SizeController {
    @Autowired
    private SizeServiceImp sizeServiceImp;

    private Logger logger = LoggerFactory.getLogger(ProductController.class);
    private Gson gson = new Gson();

    @GetMapping("")
    public ResponseEntity<?> getAllSize() {
        BaseResponse response = new BaseResponse();
        response.setStatusCode(200);
        response.setData(sizeServiceImp.getAllSize());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
