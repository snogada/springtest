package com.kakaopay.invest.service.exception;

public class ProductReadFailException extends RuntimeException {
    private static final String MESSAGE = "투자상품 조회에 실패하였습니다.";

    public ProductReadFailException(final Throwable e) {
        super(MESSAGE, e);
    }
}