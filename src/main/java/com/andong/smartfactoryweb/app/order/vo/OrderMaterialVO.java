package com.andong.smartfactoryweb.app.order.vo;

import lombok.Data;

import java.util.Date;

@Data
public class OrderMaterialVO {
    private Long orderMaterialSeq;
    private Long materialSeq;
    private Long productOrderSeq;
    private int count;
    private Date createAt;
    private Long materialTotalPrice;
    private String region;

}
