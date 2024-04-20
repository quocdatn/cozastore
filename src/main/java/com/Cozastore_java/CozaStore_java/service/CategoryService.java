package com.Cozastore_java.CozaStore_java.service;

import com.Cozastore_java.CozaStore_java.entity.CategoryEntity;
import com.Cozastore_java.CozaStore_java.payload.response.CategoryResponse;
import com.Cozastore_java.CozaStore_java.repository.CategoryRepository;
import com.Cozastore_java.CozaStore_java.service.imp.CategoryServiceImp;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryService implements CategoryServiceImp {
    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
//    @Cacheable("listCategory")
    public List<CategoryResponse> getAllCategory() {
        System.out.println("Kiểm tra category");
        List<CategoryResponse> responseList = new ArrayList<>();

        List<CategoryEntity> list = categoryRepository.findAll();

        for (CategoryEntity data : list) {
            CategoryResponse categoryResponse = new CategoryResponse();
            categoryResponse.setId(data.getId());
            categoryResponse.setName(data.getName());

            responseList.add(categoryResponse);
        }

        Gson gson = new Gson();
        String data = gson.toJson(responseList);

//        if (redisTemplate.hasKey("listCategory")) {
//            // Nếu có thì lấy giá trị lưu trữ trên redis
//            System.out.println("Có giá trị trên redis");
//            String data = redisTemplate.opsForValue().get("listCategory").toString();
//
//            Type listType = new TypeToken<ArrayList<CategoryResponse>>(){}.getType();
//            responseList = new Gson().fromJson(data, listType);
//        } else {
//            System.out.println("Không có giá trị trên redis");
//            List<CategoryEntity> list = categoryRepository.findAll();
//
//            for (CategoryEntity data : list) {
//                CategoryResponse categoryResponse = new CategoryResponse();
//                categoryResponse.setId(data.getId());
//                categoryResponse.setName(data.getName());
//
//                responseList.add(categoryResponse);
//            }
//
//            Gson gson = new Gson();
//            String data = gson.toJson(responseList);
//
//            redisTemplate.opsForValue().set("listCategory", data);
//        }


        return responseList;
    }
}
