package com.andong.smartfactoryweb.app.order.vo;

import com.andong.smartfactoryweb.app.user.vo.UserVO;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class ProductOrderVO {
    private Long productOrderSeq;
    private Long userSeq;
    private String productStatus;
    private Date orderDate;
    private String approvalId;

    //UserVO와의 관계
    private UserVO user;

    //OrderMaterialVO와의 1:N 관계
    private List<OrderMaterialVO> orderMaterials;
}
