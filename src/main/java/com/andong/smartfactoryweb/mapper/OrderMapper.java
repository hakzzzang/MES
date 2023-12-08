package com.andong.smartfactoryweb.mapper;

import com.andong.smartfactoryweb.app.order.vo.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OrderMapper {
    public List<MaterialVO> productInfo();

    public List<OrderMaterialVO> orderInfo();

    void insertOrder(OrderMaterialVO orderMaterialVO);

    void updateMaterialCount(@Param("materialName") String materialName, @Param("quantity") int quantity);

    List<OrderStatusVO> selectOrderStatus();

    List<OrderDetailStatusVO> selectDetailStatus();

    List<UserOrderStatusVO> selectUserOrderStatus(String userId);
    
}
