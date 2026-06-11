package com.example.pixelkurashi.controller;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
 
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
 
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.http.MediaType;
 
import com.example.pixelkurashi.dto.MoneyRecordCreateRequest;
import com.example.pixelkurashi.dto.MoneyRecordResponse;
import com.example.pixelkurashi.service.MoneyRecordService;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(MoneyRecordController.class)
public class MoneyRecordControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MoneyRecordService moneyRecordService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testGetAllRecords() throws Exception {
        //テストデータの準備
        List<MoneyRecordResponse> mockRecords = Arrays.asList(
            new MoneyRecordResponse(1L, LocalDate.of(2026, 6, 1), 1000, "食費", "ランチ", LocalDateTime.now(), LocalDateTime.now())
        );

        //serviceのモック設定
        when(moneyRecordService.getAllRecords()).thenReturn(mockRecords);
    
         // GETリクエストのテスト
        mockMvc.perform(get("/api/money-records"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$[0].id").value(1))
            .andExpect(jsonPath("$[0].amount").value(1000))
            .andExpect(jsonPath("$[0].category").value("食費"));
    }

     @Test
    public void testCreateRecord_Success() throws Exception {
        // テストデータの準備
        MoneyRecordCreateRequest request = new MoneyRecordCreateRequest();
        request.setRecordDate(LocalDate.of(2026, 6, 1));
        request.setAmount(1000);
        request.setCategory("食費");
        request.setMemo("ランチ");
 
        // POSTリクエストのテスト
        mockMvc.perform(post("/api/money-records")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(request)))
            .andExpect(status().isOk());
    }

    @Test
    public void testCreateRecord_ValidationError_EmptyCategory() throws Exception {
        // テストデータの準備
        MoneyRecordCreateRequest request = new MoneyRecordCreateRequest();
        request.setRecordDate(null);
        request.setAmount(null);
        request.setCategory("");
 
        // POSTリクエストのテスト
        mockMvc.perform(post("/api/money-records")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(request)))
            .andExpect(status().isBadRequest());
    }

    @Test
    public void testDeleteRecord_Success() throws Exception {
        Long testId = 1L;

        mockMvc.perform(delete("/api/money-records/" + testId))
            .andExpect(status().isOk());

        verify(moneyRecordService, times(1)).deleteRecord(testId);
    }
}
