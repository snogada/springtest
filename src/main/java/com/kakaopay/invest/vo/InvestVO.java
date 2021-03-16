package com.kakaopay.invest.vo;

import lombok.Data;

import java.sql.Date;

@Data
public class InvestVO {
    private long product_id; //상품ID
    private String title; //상품 제목
    private int total_investing_amount; //총 모집금액
    private int investing_amount; //현재 모집금액
    private int investor_num; //투자자 수
    private String invest_status; //투자모집상태(모집중, 모집완료)
    private Date start_at; //상품 모집시작기간
    private Date finished_at; //상품 모집종료기간

    private long user_id; //사용자ID
    private int amount; //투자금액
    private Date invest_date; //투자일시
}