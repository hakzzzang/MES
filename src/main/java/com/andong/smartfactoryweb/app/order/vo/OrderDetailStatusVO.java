package com.andong.smartfactoryweb.app.order.vo;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Data
@Getter
@Setter
public class OrderDetailStatusVO {

    private int productOrderSeq;
    private String materialName;
    private int count;
    private String region;
    private BigDecimal materialPrice;
    private BigDecimal materialTotalPrice;
}
