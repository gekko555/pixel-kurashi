package com.example.pixelkurashi.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class MoneyRecordCreateRequest {
    
    //記録日
    @NotNull(message = "日付は必須です")
    private LocalDate recordDate;

    //金額
    @NotNull(message = "金額は必須です")
    private Integer amount;

    //カテゴリ
    @NotBlank(message = "カテゴリは必須です")
    private String category;

    //メモ
    private String memo;

    public LocalDate getRecordDate(){
        return recordDate;
    }

    public void setRecordDate(LocalDate recordDate){
        this.recordDate = recordDate;
    }

    public Integer getAmount(){
        return amount;
    }

    public void setAmount(Integer amount){
        this.amount = amount;
    }

    public String getCategory(){
        return category;
    }

    public void setCategory(String category){
        this.category = category;
    }

    public String getMemo(){
        return memo;
    }
    
    public void setMemo(String memo){
        this.memo = memo;
    }
}