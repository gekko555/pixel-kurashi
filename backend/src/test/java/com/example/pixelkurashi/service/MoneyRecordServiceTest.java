package com.example.pixelkurashi.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
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
import com.example.pixelkurashi.dto.MoneyRecordUpdateRequest;
import com.example.pixelkurashi.entity.MoneyRecord;
import com.example.pixelkurashi.repository.MoneyRecordRepository;

@ExtendWith(MockitoExtension.class)
public class MoneyRecordServiceTest {

    @Mock
    private MoneyRecordRepository repository;

    @InjectMocks
    private MoneyRecordService service;

    // 作成成功のテスト
    @Test
    public void testCreateRecord() {
        // テストデータの準備
        MoneyRecordCreateRequest request = new MoneyRecordCreateRequest();
        request.setRecordDate(LocalDate.of(2026, 6, 1));
        request.setAmount(1000);
        request.setCategoryId(1L);
        request.setMemo("ランチ");

        // 登録処理のテスト
        service.createRecord(request);

        // repository.insert()が呼ばれたことを確認
        verify(repository).insert(any(MoneyRecord.class));
    }

    // 全件取得のテスト
    @Test
    public void testGetAllRecords() {
        // テストデータの準備
        MoneyRecordResponse response = new MoneyRecordResponse(
        1L, 
        LocalDate.of(2026, 6, 1), 
        1000, 
        1L, 
        "食費", 
        "ランチ", 
        LocalDateTime.now(), 
        LocalDateTime.now()
    );

        when(repository.findAll()).thenReturn(Arrays.asList(response));

        // 全件取得のテスト
        List<MoneyRecordResponse> responses = service.getAllRecords();

        // 結果の確認
        assertEquals(1, responses.size());
        assertEquals(1L, responses.get(0).getId());
        assertEquals(1000, responses.get(0).getAmount());
        assertEquals(1L, responses.get(0).getCategoryId());
        assertEquals("食費", responses.get(0).getCategoryName());
    }

    // 削除成功のテスト
    @Test
    public void testDeleteRecord() throws Exception {
        Long testId = 1L;

        service.deleteRecord(testId);

        verify(repository).deleteById(testId);
    }

    // 更新成功のテスト
    @Test
    public void testUpdateRecord() {
        // テストデータの準備
        MoneyRecordUpdateRequest request = new MoneyRecordUpdateRequest();
        request.setId(1L);
        request.setRecordDate(LocalDate.of(2026, 6, 1));
        request.setAmount(1000);
        request.setCategoryId(1L);
        request.setMemo("ランチ");

        // repositoryのモック設定（voidメソッドなので何も返さない）
        doNothing().when(repository).updateById(any(MoneyRecord.class));

        // 更新処理のテスト
        service.updateById(request);

        // repositoryメソッドが呼ばれたことを検証
        verify(repository).updateById(any(MoneyRecord.class));
    }

}
