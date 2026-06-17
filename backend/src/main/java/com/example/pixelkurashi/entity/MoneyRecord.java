package com.example.pixelkurashi.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class MoneyRecord {

    private Long id;

    //記録日
    private LocalDate recordDate;

    //金額(収入:+, 支出:-)
    private Integer amount;

    //カテゴリ
    private Long categoryId;

    //メモ
    private String memo;

    //作成日時
    private LocalDateTime createdAt;

    //更新日時
    private LocalDateTime updatedAt;

    //コンストラクタ
    public MoneyRecord() {}

    //引数付きコンストラクタ
    public MoneyRecord(Long id, LocalDate recordDate, Integer amount, Long categoryId, String memo) {
        this.id = id;
        this.recordDate = recordDate;
        this.amount = amount;
        this.categoryId = categoryId;
        this.memo = memo;
    }

    //アクセサ＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝
    public Long getId(){
        return id;
    }

    public void setId(Long id){
        this.id = id;
    }

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

    public Long getCategoryId(){
        return categoryId;
    }

    public void setCategoryId(Long categoryId){
        this.categoryId = categoryId;
    }

    public String getMemo(){
        return memo;
    }

    public void setMemo(String memo){
        this.memo = memo;
    }

    public LocalDateTime getCreatedAt(){
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt){
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt(){
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt){
        this.updatedAt = updatedAt;
    }
    
}
