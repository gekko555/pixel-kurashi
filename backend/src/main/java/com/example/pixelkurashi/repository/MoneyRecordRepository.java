package com.example.pixelkurashi.repository;

import java.time.LocalDate;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.example.pixelkurashi.entity.MoneyRecord;

@Mapper
@Repository
public interface MoneyRecordRepository {

    // 支出と収入を登録
    void insert(MoneyRecord moneyRecord);

    // すべての支出と収入を取得
    List<MoneyRecord> findAll();

    // 月ごとの支出と収入を取得
    List<MoneyRecord> findByMonth(@Param("year")int year, @Param("month") int month);

    // 日ごとの支出と収入を取得
    List<MoneyRecord> findByDay(@Param("date")LocalDate date);

    // IDで支出と収入を削除
    void deleteById(@Param("id")Long id);

    // IDで支出と収入を更新
    void updateById(MoneyRecord moneyRecord);

    
    
    
}
