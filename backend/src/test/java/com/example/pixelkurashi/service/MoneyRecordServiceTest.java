package com.example.pixelkurashi.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.pixelkurashi.dto.MoneyRecordCreateRequest;
import com.example.pixelkurashi.dto.MoneyRecordResponse;
import com.example.pixelkurashi.entity.MoneyRecord;
import com.example.pixelkurashi.repository.MoneyRecordRepository;

@ExtendWith(MockitoExtension.class)
public class MoneyRecordServiceTest {
    
    @Mock
    private MoneyRecordRepository repository;

    @InjectMocks
    private MoneyRecordService service;

    @Test
    public void testCreateRecord() {
        // テストデータの準備
        MoneyRecordCreateRequest request = new MoneyRecordCreateRequest();
        request.setRecordDate(LocalDate.of(2026, 6, 1));
        request.setAmount(1000);
        request.setCategory("食費");
        request.setMemo("ランチ");
 
        // 登録処理のテスト
        service.createRecord(request);
 
        // repository.insert()が呼ばれたことを確認
        verify(repository).insert(any(MoneyRecord.class));
    }

    @Test
    public void testGetAllRecords() {
        // テストデータの準備
        MoneyRecord entity = new MoneyRecord();
        entity.setId(1L);
        entity.setRecordDate(LocalDate.of(2026, 6, 1));
        entity.setAmount(1000);
        entity.setCategory("食費");
        entity.setMemo("ランチ");
        entity.setCreatedAt(LocalDateTime.now());
        entity.setUpdatedAt(LocalDateTime.now());
 
        when(repository.findAll()).thenReturn(Arrays.asList(entity));
 
        // 全件取得のテスト
        List<MoneyRecordResponse> responses = service.getAllRecords();
 
        // 結果の確認
        assertEquals(1, responses.size());
        assertEquals(1L, responses.get(0).getId());
        assertEquals(1000, responses.get(0).getAmount());
        assertEquals("食費", responses.get(0).getCategory());
    }


}
