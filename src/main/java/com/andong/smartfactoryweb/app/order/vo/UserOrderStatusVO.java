package com.andong.smartfactoryweb.app.order.vo;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Data
@Getter
@Setter
public class UserOrderStatusVO {

    private Long productOrderSeq;
    private Date orderDate;
    private String productStatus;
    private String userName;

}
