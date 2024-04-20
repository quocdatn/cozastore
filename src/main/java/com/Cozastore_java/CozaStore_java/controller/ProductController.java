package com.Cozastore_java.CozaStore_java.controller;

import com.Cozastore_java.CozaStore_java.payload.response.BaseResponse;
import com.Cozastore_java.CozaStore_java.service.imp.ProductServiceImp;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/product")
public class ProductController {
    // Cache dùng để lưu trữ dữ liệu lớn ít thay đổi hoặc hoàn toàn không thay đổi (master data)
    // mem cache: lưu trữ trên ram
    @Autowired
    private ProductServiceImp productServiceIpm;

    private Logger logger = LoggerFactory.getLogger(ProductController.class);
    private Gson gson = new Gson();

    @GetMapping("/category/{id}")
    public ResponseEntity<?> getAllProductByCategory(@PathVariable int id) {
        logger.info("Tham số id: " + id);
        BaseResponse response = new BaseResponse();
        response.setStatusCode(200);
        response.setData(productServiceIpm.getProductByCategory(id));

        logger.info(gson.toJson(response));

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity<?> getAllProduct() {
        BaseResponse response = new BaseResponse();
        response.setStatusCode(200);
        response.setData(productServiceIpm.getAllProduct());

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getProductById(@PathVariable int id) {
        BaseResponse response = new BaseResponse();
        response.setStatusCode(200);
        response.setData(productServiceIpm.getProductByID(id));

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
