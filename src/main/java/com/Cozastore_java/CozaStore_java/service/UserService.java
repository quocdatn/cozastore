package com.Cozastore_java.CozaStore_java.service;

import com.Cozastore_java.CozaStore_java.entity.UserEntity;
import com.Cozastore_java.CozaStore_java.exception.CustomException;
import com.Cozastore_java.CozaStore_java.payload.request.SignupRequest;
import com.Cozastore_java.CozaStore_java.repository.UserRepository;
import com.Cozastore_java.CozaStore_java.service.imp.UserServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserServiceImp {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public boolean addUser(SignupRequest request) {
        boolean isSuccess = false;
        try {
            UserEntity user = new UserEntity();
            user.setUsername(request.getUsername());
            user.setPassword(passwordEncoder.encode(request.getPassword()));
            user.setEmail(request.getEmail());

            userRepository.save(user);
            isSuccess = true;
        } catch (Exception e) {
            throw new CustomException("Lỗi thêm user " + e.getMessage());
        }
        return isSuccess;
    }
}
