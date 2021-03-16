package com.kakaopay.invest.service;

import com.kakaopay.invest.vo.InvestVO;

import java.util.List;

public interface InvestService {
    //전체 투자 상품 조회
    public List<InvestVO> listProduct() throws  Exception;
    //투자하기
    public int investProduct(InvestVO investvo) throws  Exception;
    //투자상품 조회
    public List<InvestVO> listInvest(Integer user_id) throws  Exception;


    //초기화
    public void init() throws  Exception;
}
