package com.Cozastore_java.CozaStore_java.controller;

import com.Cozastore_java.CozaStore_java.exception.CustomException;
import com.Cozastore_java.CozaStore_java.payload.request.SignupRequest;
import com.Cozastore_java.CozaStore_java.payload.response.BaseResponse;
import com.Cozastore_java.CozaStore_java.service.imp.UserServiceImp;
import com.Cozastore_java.CozaStore_java.utils.JwtHelper;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
public class LoginController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtHelper jwtHelper;

    @Autowired
    private UserServiceImp userServiceImp;

    /**
     * statusCode:200
     * message: ""
     * data: kiểu gì cũng được
     */

    @RequestMapping(value = "/signin", method = RequestMethod.POST)
    public ResponseEntity<?> signin(
            @RequestParam String email,
            @RequestParam String password
    ) {
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(email, password);
        authenticationManager.authenticate(token);

        // Nếu chứng thực thành công sẽ chạy code tiếp theo, nếu thất bại sẽ văng lỗi chưa chứng thực
        String jwt = jwtHelper.generateToken(email);
        BaseResponse response = new BaseResponse();
        response.setStatusCode(200);
        response.setData(jwt);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public ResponseEntity<?> signup(@Valid SignupRequest request, BindingResult result) {

        List<FieldError> list = result.getFieldErrors();
        for (FieldError data : list) {
            throw new CustomException(data.getDefaultMessage());
//            System.out.println("kiểm tra: " + data.getField() + " - " + data.getDefaultMessage());
        }

        boolean isSuccess = userServiceImp.addUser(request);

        BaseResponse response = new BaseResponse();
        response.setStatusCode(200);
        response.setData(isSuccess);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
