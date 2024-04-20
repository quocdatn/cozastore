package com.Cozastore_java.CozaStore_java.service.imp;

import com.Cozastore_java.CozaStore_java.payload.request.SignupRequest;

public interface UserServiceImp {
    boolean addUser(SignupRequest request);
}
