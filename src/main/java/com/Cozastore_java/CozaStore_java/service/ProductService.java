package com.Cozastore_java.CozaStore_java.service;

import com.Cozastore_java.CozaStore_java.entity.ProductEntity;
import com.Cozastore_java.CozaStore_java.payload.response.CategoryResponse;
import com.Cozastore_java.CozaStore_java.payload.response.ColorResponse;
import com.Cozastore_java.CozaStore_java.payload.response.ProductResponse;
import com.Cozastore_java.CozaStore_java.payload.response.SizeResponse;
import com.Cozastore_java.CozaStore_java.repository.ProductRepository;
import com.Cozastore_java.CozaStore_java.service.imp.ProductServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService implements ProductServiceImp {
    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<ProductResponse> getProductByCategory(int id) {
        List<ProductEntity> list = productRepository.findByCategoryId(id);
        List<ProductResponse> responseList = new ArrayList<>();
        System.out.println(list.size());
        for(ProductEntity data : list){
            ProductResponse productResponse = new ProductResponse();
            productResponse.setId(data.getId());
            productResponse.setName(data.getName());
            productResponse.setPrice(data.getPrice());
            productResponse.setImage(data.getImage());

            responseList.add(productResponse);
        }
        return responseList;
    }

    @Override
    public List<ProductResponse> getAllProduct() {
        List<ProductEntity> list = productRepository.findAll();
        List<ProductResponse> responseList = new ArrayList<>();
        for(ProductEntity data : list){
            ProductResponse productResponse = new ProductResponse();
            productResponse.setId(data.getId());
            productResponse.setName(data.getName());
            productResponse.setPrice(data.getPrice());
            productResponse.setDescription(data.getDescription());
            productResponse.setImage(data.getImage());
            productResponse.setQuantity(data.getQuantity());
            productResponse.setImageDetail(data.getImageDetail());

            CategoryResponse categoryResponse = new CategoryResponse();
            categoryResponse.setId(data.getCategory().getId());
            categoryResponse.setName(data.getCategory().getName());

            SizeResponse sizeResponse = new SizeResponse();
            sizeResponse.setId(data.getSize().getId());
            sizeResponse.setName(data.getSize().getName());

            ColorResponse colorResponse = new ColorResponse();
            colorResponse.setId(data.getColor().getId());
            colorResponse.setName(data.getColor().getName());

            productResponse.setCategory(categoryResponse);
            productResponse.setSize(sizeResponse);
            productResponse.setColor(colorResponse);

            responseList.add(productResponse);
        }

        return responseList;
    }

    @Override
    public ProductResponse getProductByID(int id) {
        ProductEntity data = productRepository.findById(id).get();
        ProductResponse productResponse = new ProductResponse();
        productResponse.setId(data.getId());
        productResponse.setName(data.getName());
        productResponse.setPrice(data.getPrice());
        productResponse.setImage(data.getImage());
        productResponse.setDescription(data.getDescription());
        productResponse.setQuantity(data.getQuantity());
        productResponse.setImageDetail(data.getImageDetail());

        CategoryResponse categoryResponse = new CategoryResponse();
        categoryResponse.setName(data.getCategory().getName());
        categoryResponse.setId(data.getCategory().getId());

        productResponse.setCategory(categoryResponse);



        return productResponse;
    }
}
