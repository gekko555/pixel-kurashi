package com.example.pixelkurashi.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class MoneyRecordWithCategory {
    // レコードID
    private Long id;

    // 記録日
    private LocalDate recordDate;

    // 金額
    private Integer amount;
    
    // カテゴリID
    private Long categoryId;

    // カテゴリ名
    private String categoryName;

    // 備考
    private String memo;
    
    // 作成日時
    private LocalDateTime createdAt;
    
    // 更新日時
    private LocalDateTime updatedAt;

    public MoneyRecordWithCategory(Long id, LocalDate recordDate, Integer amount, Long categoryId, String categoryName, String memo, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.recordDate = recordDate;
        this.amount = amount;
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.memo = memo;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public LocalDate getRecordDate() {
        return recordDate;
    }

    public Integer getAmount() {
        return amount;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public String getCategoryName() {
        return categoryName;
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
