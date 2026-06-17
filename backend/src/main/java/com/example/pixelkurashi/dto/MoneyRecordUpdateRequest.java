package com.example.pixelkurashi.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

public class MoneyRecordUpdateRequest {
    // 更新対象のレコードID
    @NotNull
    private Long id;

    // 更新する日付
    @NotNull
    private LocalDate recordDate;

    // 更新する金額
    @NotNull
    private Integer amount;
    
    // 更新するカテゴリ
    @NotNull
    private Long categoryId;
    
    // 更新するメモ
    private String memo;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getRecordDate() {
        return recordDate;
    }

    public void setRecordDate(LocalDate recordDate) {
        this.recordDate = recordDate;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }
}