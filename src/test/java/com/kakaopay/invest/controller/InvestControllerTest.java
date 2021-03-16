package com.kakaopay.invest.controller;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.kakaopay.invest.service.InvestService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.nio.charset.Charset;

@SpringBootTest
@AutoConfigureMockMvc
public class InvestControllerTest {
    private static final Logger logger = LoggerFactory.getLogger(InvestController.class);

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private InvestController investController;

    @Autowired
    private InvestService investService;

    private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(),
            Charset.forName("utf8"));

    @Test
    @DisplayName("===========초기화============")
    public void init() throws Exception {
        investService.init();
    }

    @Test
    @DisplayName("===========전체상품조회============")
    public void getProductList() throws Exception {

        init();

        //모집중 TEST
        mockMvc.perform(get("/product"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].title").value("부동산투자상품"))
                .andExpect(jsonPath("$[0].invest_status").value("모집중"))
                .andDo(print());

        
        mockMvc.perform(post("/invest/1").header("X-USER-ID" , 1).contentType(contentType).queryParam("amount", "10000"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("SUCCESS"))
                .andDo(print());

        //모집완료 TEST
        mockMvc.perform(get("/product"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].title").value("부동산투자상품"))
                .andExpect(jsonPath("$[0].invest_status").value("모집완료"))
                .andDo(print());
        ;

    }

    @Test
    @DisplayName("===========삼품투자하기============")
    public void insertInvest() throws Exception {

        init();

        //SUCCESS TEST
        mockMvc.perform(post("/invest/1").header("X-USER-ID" , 1).contentType(contentType).queryParam("amount", "10000"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("SUCCESS"))
                .andDo(print())
        ;

        //sold-out TEST
        mockMvc.perform(post("/invest/1").header("X-USER-ID" , 1).contentType(contentType).queryParam("amount", "10000"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("SOLD_OUT"))
                .andDo(print())
        ;
    }

    @Test
    @DisplayName("===========나의투자상품조회============")
    public void getMyInvest() throws Exception {

        init();

        //insert test data
        mockMvc.perform(post("/invest/1").header("X-USER-ID" , 1).contentType(contentType).queryParam("amount", "10000"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("SUCCESS"))
                .andDo(print())
        ;

        //투자상품조회
        mockMvc.perform(get("/invest")
                .header("X-USER-ID" , 1))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].title").value("부동산투자상품"))
                .andExpect(jsonPath("$[0].amount").value(10000))
                .andDo(print());
    }
}