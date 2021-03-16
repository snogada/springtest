package com.kakaopay.invest.mapper;

import com.kakaopay.invest.vo.InvestVO;

import java.util.List;

public interface InvestMapper {
    //전체 투자 상품 조회
    public List<InvestVO> listProduct() throws Exception;
    //투자하기
    public int investProduct(InvestVO investVo) throws Exception;
    //나의 투자상품 조회
    public List<InvestVO> listInvest(Integer user_id) throws Exception;



    //초기화 - delete
    public int deleteInitProduct() throws Exception;
    public int deleteInitInvest() throws Exception;

    //초기화 - insert
    public int insertInitProduct() throws Exception;
}
