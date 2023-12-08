package com.andong.smartfactoryweb.app.order.vo;

import lombok.Data;

import java.util.Date;

@Data
public class VIPUserVO {
    private String userName;
    private String email;
    private Long money;
    private Date orderDate;
    private Long orderCount;
}
