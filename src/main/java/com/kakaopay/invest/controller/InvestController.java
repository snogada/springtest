package com.kakaopay.invest.controller;

import com.kakaopay.invest.com.ErrorResponse;
import com.kakaopay.invest.com.SuccessResponse;
import com.kakaopay.invest.mapper.InvestMapper;
import com.kakaopay.invest.service.InvestService;
import com.kakaopay.invest.service.exception.InvestFailException;
import com.kakaopay.invest.service.exception.InvestReadFailException;
import com.kakaopay.invest.service.exception.InvestSoldOutException;
import com.kakaopay.invest.service.exception.ProductReadFailException;
import com.kakaopay.invest.vo.InvestVO;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("")
@AllArgsConstructor
public class InvestController {
    private static final Logger logger = LoggerFactory.getLogger(InvestController.class);
    final InvestService investService;

    //전체 투자 상품 조회
    @RequestMapping(value="/product",method= RequestMethod.GET)
    public ResponseEntity<List<InvestVO>> listProduct() throws Exception {
        logger.info("=========listProduct==========");

        return new ResponseEntity<>(investService.listProduct(), HttpStatus.OK);
    }

    //투자하기
    @RequestMapping(value="/invest/{product_id}", method=RequestMethod.POST)
    public ResponseEntity<SuccessResponse> investProduct(
            @RequestHeader(value="X-USER-ID") int user_id,
            @RequestParam(value="amount") int amount,
            @PathVariable("product_id") int product_id
            ) throws Exception {

        ResponseEntity<String> entity;
        InvestVO investvo = new InvestVO();

        investvo.setAmount(amount);
        investvo.setUser_id(user_id);
        investvo.setProduct_id(product_id);
        investService.investProduct(investvo);

        SuccessResponse successResponse = new SuccessResponse();
        successResponse.setMessage("투자에 성공했습니다.");
        successResponse.setStatus("SUCCESS");

        return new ResponseEntity<>(successResponse, HttpStatus.OK);
    }

    //나의 투자상품 조회
    @RequestMapping(value="/invest",method= RequestMethod.GET)
    public ResponseEntity<List<InvestVO>> listInvest(
            @RequestHeader(value="X-USER-ID") Integer user_id
    ) throws Exception {
        logger.info("==========readUser============");

        return new ResponseEntity<>(investService.listInvest(user_id), HttpStatus.OK);
    }

    @ExceptionHandler(ProductReadFailException.class)
    protected ResponseEntity<ErrorResponse> handleProductReadException(ProductReadFailException e) {
        logger.error("ProductReadFailException : ", e);

        final ErrorResponse response = new ErrorResponse();
        response.setStatus("READ_FAIL");
        response.setMessage(e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvestFailException.class)
    protected ResponseEntity<ErrorResponse> handleInvestException(InvestFailException e) {
        logger.error("InvestFailException : ", e);

        final ErrorResponse response = new ErrorResponse();
        response.setStatus("INSERT_FAIL");
        response.setMessage(e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvestSoldOutException.class)
    protected ResponseEntity<ErrorResponse> handleInvestSoldOutException(InvestSoldOutException e) {
        logger.error("InvestSoldOutException : ", e);

        final ErrorResponse response = new ErrorResponse();
        response.setStatus("SOLD_OUT");
        response.setMessage(e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @ExceptionHandler(InvestReadFailException.class)
    protected ResponseEntity<ErrorResponse> handleInvestReadException(InvestReadFailException e) {
        logger.error("InvestReadFailException : ", e);

        final ErrorResponse response = new ErrorResponse();
        response.setStatus("READ_FAIL");
        response.setMessage(e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
