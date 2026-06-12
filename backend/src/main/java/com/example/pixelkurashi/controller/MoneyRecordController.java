package com.example.pixelkurashi.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.pixelkurashi.dto.MoneyRecordCreateRequest;
import com.example.pixelkurashi.dto.MoneyRecordResponse;
import com.example.pixelkurashi.dto.MoneyRecordUpdateRequest;
import com.example.pixelkurashi.service.MoneyRecordService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/money-records")
@CrossOrigin(origins = "http://localhost:5173")
public class MoneyRecordController {

    /**
     * 家計簿記録サービス
     */
    private final MoneyRecordService service;
    
    public MoneyRecordController(MoneyRecordService service) {
        this.service = service;
    }

    /**
     * 家計簿記録を登録する
     * @param request 登録する家計簿記録のリクエスト
     */
    @PostMapping
    public void createRecord(@Valid @RequestBody MoneyRecordCreateRequest request) {
        service.createRecord(request);
    }

    /**
     * 全ての家計簿記録を取得する
     * @return 家計簿記録のリスト
     */
    @GetMapping
    public List<MoneyRecordResponse> getAllRecords() {
        return service.getAllRecords();
    }

    /**
     * 指定した月の家計簿記録を取得する
     * @param year 年
     * @param month 月 (1-12)
     * @return 家計簿記録のリスト
     */
    @GetMapping("/month/{year}/{month}")
    public List<MoneyRecordResponse> getRecordsByMonth(
        @PathVariable int year,
        @PathVariable int month) {
        return service.getRecordsByMonth(year, month);
    }

    /**
     * 指定した日付の家計簿記録を取得する
     * @param date 日付
     * @return 家計簿記録のリスト
     */
    @GetMapping("/day/{date}")
    public List<MoneyRecordResponse> getRecordsByDay(@PathVariable LocalDate date) {
        return service.getRecordsByDay(date);
    }

    /**
     * 指定したIDの家計簿記録を削除する
     * @param id 削除する家計簿記録のID
     */
    @DeleteMapping("/{id}")
    public void deleteRecord(@PathVariable Long id) {
        service.deleteRecord(id);
    }

    @PutMapping("/{id}")
    public void updateRecord(@PathVariable Long id, @Valid @RequestBody MoneyRecordUpdateRequest request) {
        request.setId(id);
        service.updateById(request);
    }
    
}
