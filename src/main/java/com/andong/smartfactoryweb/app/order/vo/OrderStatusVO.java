package com.andong.smartfactoryweb.app.order.vo;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Data
@Setter
@Getter
public class OrderStatusVO {
    private Long productOrderSeq;
    private Date orderDate;
    private String productStatus;
    private String userName;
}
