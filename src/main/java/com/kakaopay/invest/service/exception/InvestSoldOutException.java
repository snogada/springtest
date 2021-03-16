package com.kakaopay.invest.service.exception;

public class InvestSoldOutException extends RuntimeException {
    private static final String MESSAGE = "투자상품 판매가 완료되었습니다.";

    public InvestSoldOutException() {
        super(MESSAGE);
    }
}
