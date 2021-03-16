package com.kakaopay.invest.service;

import com.kakaopay.invest.controller.InvestController;
import com.kakaopay.invest.mapper.InvestMapper;
import com.kakaopay.invest.service.exception.InvestFailException;
import com.kakaopay.invest.service.exception.InvestReadFailException;
import com.kakaopay.invest.service.exception.InvestSoldOutException;
import com.kakaopay.invest.service.exception.ProductReadFailException;
import com.kakaopay.invest.vo.InvestVO;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("InvestService")
@AllArgsConstructor
public class InvestServiceImpl implements InvestService{
    private static final Logger logger = LoggerFactory.getLogger(InvestController.class);
    final InvestMapper investMapper;

    @Override
    public List<InvestVO> listProduct(){
        try{
            return investMapper.listProduct();
        }catch(Exception e){
            logger.error("product list read fail : ", e);
            throw new ProductReadFailException(e);
        }
    }

    @Override
    public int investProduct(InvestVO investvo){
        int retNum =0;

        try{
            retNum = investMapper.investProduct(investvo);

            logger.info("retNum : " + retNum);

        }catch(Exception e){
            logger.error("invest fail : ", e);
            throw new InvestFailException(e);
        }

        if(retNum == 0){
            logger.info("invest fail : Sold Out");
            throw new InvestSoldOutException();
        }
        return retNum;
    }

    @Override
    public List<InvestVO> listInvest(Integer user_id){
        try{
            return investMapper.listInvest(user_id);
        }catch(Exception e){
            logger.error("invest list read fail : ", e);
            throw new InvestReadFailException(e);
        }
    }

    @Override
    public void init(){
        try {
            investMapper.deleteInitProduct();
            investMapper.deleteInitInvest();
            investMapper.insertInitProduct();
        }catch(Exception e){
            logger.error("init fail : ", e);
        }
    }
}
