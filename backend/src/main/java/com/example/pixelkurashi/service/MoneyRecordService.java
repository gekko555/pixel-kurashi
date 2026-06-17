package com.example.pixelkurashi.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import com.example.pixelkurashi.dto.MoneyRecordCreateRequest;
import com.example.pixelkurashi.dto.MoneyRecordResponse;
import com.example.pixelkurashi.dto.MoneyRecordUpdateRequest;
import com.example.pixelkurashi.dto.MoneyRecordWithCategory;
import com.example.pixelkurashi.entity.MoneyRecord;
import com.example.pixelkurashi.repository.MoneyRecordRepository;

@Service
public class MoneyRecordService {

    private final MoneyRecordRepository repository;

    private final CategoryMasterService categoryMasterService;

    public MoneyRecordService(MoneyRecordRepository repository, CategoryMasterService categoryMasterService) {
        this.repository = repository;
        this.categoryMasterService = categoryMasterService;
    }

    /**
     * 家計簿記録を登録する
     * 
     * @param moneyRecord 登録する家計簿記録
     */
    public void createRecord(MoneyRecordCreateRequest moneyRecord) {
        MoneyRecord entity = toEntity(moneyRecord);
        repository.insert(entity);
    }

    /**
     * 全ての家計簿記録を取得する
     * 
     * @return 家計簿記録のリスト
     */
    public List<MoneyRecordResponse> getAllRecords() {
        List<MoneyRecordWithCategory> records = repository.findAll();
        List<MoneyRecordResponse> responses = new ArrayList<>();
        for (MoneyRecordWithCategory record : records) {
            responses.add(new MoneyRecordResponse(
                    record.getId(),
                    record.getRecordDate(),
                    record.getAmount(),
                    record.getCategoryId(),
                    record.getCategoryName(),
                    record.getMemo(),
                    record.getCreatedAt(),
                    record.getUpdatedAt()));
        }
        return responses;
    }

    /**
     * IDで家計簿記録を更新する
     * 
     * @param moneyRecordUpdateRequest 更新する家計簿記録
     */
    public void updateById(MoneyRecordUpdateRequest moneyRecordUpdateRequest) {
        MoneyRecord entity = toEntity(moneyRecordUpdateRequest);
        repository.updateById(entity);

    }

    /**
     * 指定した月の家計簿記録を取得する
     * 
     * @param year  年
     * @param month 月 (1-12)
     * @return 家計簿記録のリスト
     */
    public List<MoneyRecordResponse> getRecordsByMonth(int year, int month) {
        LocalDate startDate = LocalDate.of(year, month, 1);
        LocalDate endDate = startDate.plusMonths(1);
        List<MoneyRecordWithCategory> records = repository.findByMonth(startDate, endDate);
        List<MoneyRecordResponse> responses = new ArrayList<>();
        for (MoneyRecordWithCategory record : records) {
            responses.add(new MoneyRecordResponse(
                    record.getId(),
                    record.getRecordDate(),
                    record.getAmount(),
                    record.getCategoryId(),
                    record.getCategoryName(),
                    record.getMemo(),
                    record.getCreatedAt(),
                    record.getUpdatedAt()));
        }
        return responses;
    }

    /**
     * 指定した日付の家計簿記録を取得する
     * 
     * @param date 日付
     * @return 家計簿記録のリスト
     */
    public List<MoneyRecordResponse> getRecordsByDay(LocalDate date) {
        List<MoneyRecordWithCategory> records = repository.findByDay(date);
        List<MoneyRecordResponse> responses = new ArrayList<>();
        for (MoneyRecordWithCategory record : records) {
            responses.add(new MoneyRecordResponse(
                    record.getId(),
                    record.getRecordDate(),
                    record.getAmount(),
                    record.getCategoryId(),
                    record.getCategoryName(),
                    record.getMemo(),
                    record.getCreatedAt(),
                    record.getUpdatedAt()));
        }
        return responses;
    }

    /**
     * 指定したIDの家計簿記録を削除する
     * 
     * @param id 削除する家計簿記録のID
     */
    public void deleteRecord(Long id) {
        repository.deleteById(id);
    }

    /**
     * リクエストDTOをEntityに変換する
     * 
     * @param request リクエストDTO
     * @return 変換後のEntity
     */
    private MoneyRecord toEntity(MoneyRecordCreateRequest request) {
        MoneyRecord entity = new MoneyRecord();
        entity.setRecordDate(request.getRecordDate());
        entity.setAmount(request.getAmount());
        entity.setCategoryId(request.getCategoryId());
        entity.setMemo(request.getMemo());
        return entity;
    }

    /**
     * EntityをレスポンスDTOに変換する
     * 
     * @param entity Entity
     * @return 変換後のレスポンスDTO
     */
    private MoneyRecordResponse toResponse(MoneyRecord entity) {
        return new MoneyRecordResponse(
                entity.getId(),
                entity.getRecordDate(),
                entity.getAmount(),
                entity.getCategoryId(),
                null,
                entity.getMemo(),
                entity.getCreatedAt(),
                entity.getUpdatedAt());
    }

    /**
     * リクエストDTOをEntityに変換する
     * 
     * @param request リクエストDTO
     * @return 変換後のEntity
     */
    private MoneyRecord toEntity(MoneyRecordUpdateRequest request) {
        MoneyRecord entity = new MoneyRecord();
        entity.setId(request.getId());
        entity.setRecordDate(request.getRecordDate());
        entity.setAmount(request.getAmount());
        entity.setCategoryId(request.getCategoryId());
        entity.setMemo(request.getMemo());
        return entity;
    }

}
