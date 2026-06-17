package com.example.pixelkurashi.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.pixelkurashi.dto.CategoryMasterResponse;
import com.example.pixelkurashi.entity.CategoryMaster;
import com.example.pixelkurashi.repository.CategoryMasterRepository;

@Service
public class CategoryMasterService {

    private final CategoryMasterRepository repository;

    public CategoryMasterService(CategoryMasterRepository repository) {
        this.repository = repository;
    }

    public List<CategoryMasterResponse> getAllCategories() {
        List<CategoryMaster> categoryMasterList = repository.findAll();
        List<CategoryMasterResponse> response = new ArrayList<>();
        for (CategoryMaster category : categoryMasterList) {
            response.add(new CategoryMasterResponse(
                category.getId(),
                category.getName(),
                category.getCreatedAt(),
                category.getUpdatedAt()
            ));
        }
        return response;
    }
}
