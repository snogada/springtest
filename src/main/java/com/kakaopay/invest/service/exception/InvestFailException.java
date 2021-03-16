package com.kakaopay.invest.service.exception;

public class InvestFailException extends RuntimeException {
    private static final String MESSAGE = "투자상품 투자에 실패하였습니다.";

    public InvestFailException(final Throwable e) {
        super(MESSAGE, e);
    }
}
