package com.Cozastore_java.CozaStore_java.service;

import com.Cozastore_java.CozaStore_java.entity.SizeEntity;
import com.Cozastore_java.CozaStore_java.payload.response.SizeResponse;
import com.Cozastore_java.CozaStore_java.repository.SizeRepository;
import com.Cozastore_java.CozaStore_java.service.imp.SizeServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SizeService implements SizeServiceImp {
    @Autowired
    private SizeRepository sizeRepository;

    @Override
    public List<SizeResponse> getAllSize() {
        List<SizeEntity> list = sizeRepository.findAll();
        List<SizeResponse> responseList = new ArrayList<>();

        for(SizeEntity data : list){
            SizeResponse sizeResponse = new SizeResponse();
            sizeResponse.setId(data.getId());
            sizeResponse.setName(data.getName());

            responseList.add(sizeResponse);
        }
        return responseList;
    }
}
