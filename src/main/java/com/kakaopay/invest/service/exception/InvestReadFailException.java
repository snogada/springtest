package com.kakaopay.invest.service.exception;

public class InvestReadFailException extends RuntimeException {
    private static final String MESSAGE = "나의 투자상품 조회에 실패하였습니다.";

    public InvestReadFailException(final Throwable e) {
        super(MESSAGE, e);
    }
}
