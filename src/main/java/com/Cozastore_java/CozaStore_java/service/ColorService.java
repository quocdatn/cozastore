package com.Cozastore_java.CozaStore_java.service;

import com.Cozastore_java.CozaStore_java.entity.ColorEntity;
import com.Cozastore_java.CozaStore_java.payload.response.ColorResponse;
import com.Cozastore_java.CozaStore_java.repository.ColorRepository;
import com.Cozastore_java.CozaStore_java.service.imp.ColorServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ColorService implements ColorServiceImp {
    @Autowired
    private ColorRepository colorRepository;

    @Override
    public List<ColorResponse> getAllColor() {
        List<ColorEntity> list = colorRepository.findAll();
        List<ColorResponse> responseList = new ArrayList<>();

        for (ColorEntity data : list) {
            ColorResponse colorResponse = new ColorResponse();
            colorResponse.setId(data.getId());
            colorResponse.setName(data.getName());

            responseList.add(colorResponse);
        }
        return responseList;
    }
}
