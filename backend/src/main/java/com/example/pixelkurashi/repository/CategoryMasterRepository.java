package com.example.pixelkurashi.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.example.pixelkurashi.entity.CategoryMaster;

@Mapper
@Repository
public interface CategoryMasterRepository {

    // すべてのカテゴリを取得
    List<CategoryMaster> findAll();
    
}
