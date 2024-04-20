package com.Cozastore_java.CozaStore_java.service.imp;

import com.Cozastore_java.CozaStore_java.payload.response.ProductResponse;

import java.util.List;

public interface ProductServiceImp {
    List<ProductResponse> getProductByCategory(int id);
    List<ProductResponse> getAllProduct();
    ProductResponse getProductByID(int id);
}
