package com.example.pixelkurashi.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;


public class MoneyRecordResponse {
    //ID
    private Long id;
    //記録日
    private LocalDate recordDate;
    //金額
    private Integer amount;
    //カテゴリ
    private String category;
    //メモ
    private String memo;
    //作成日時
    private LocalDateTime createdAt;
    //更新日時
    private LocalDateTime updatedAt;

    public MoneyRecordResponse(Long id, LocalDate recordDate, Integer amount, String category, String memo, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.recordDate = recordDate;
        this.amount = amount;
        this.category = category;
        this.memo = memo;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Long getId() {
        return id;
    }

    public LocalDate getRecordDate() {
        return recordDate;
    }


    public Integer getAmount() {
        return amount;
    }


    public String getCategory() {
        return category;
    }

    public String getMemo() {
        return memo;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }


}
