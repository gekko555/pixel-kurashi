package com.example.pixelkurashi.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.pixelkurashi.dto.CategoryMasterResponse;
import com.example.pixelkurashi.service.CategoryMasterService;

@RestController
@RequestMapping("/api/categories")
@CrossOrigin(origins = "http://localhost:5173")
public class CategoryController {

    private final CategoryMasterService masterService;

    public CategoryController(CategoryMasterService masterService) {
        this.masterService = masterService;
    }

    @GetMapping
    public List<CategoryMasterResponse> getAllCategories() {
        return masterService.getAllCategories();
    }
    
}
