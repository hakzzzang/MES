package com.andong.smartfactoryweb.mapper;

import com.andong.smartfactoryweb.app.order.vo.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OrderMapper {
    List<MaterialVO> productInfo();

    List<OrderMaterialVO> orderInfo();

    public int insertProductOrder(ProductOrderVO productOrderVO);

    void insertOrderMaterial(OrderMaterialVO orderMaterialVO);

    void updateMaterialCount(@Param("materialName") String materialName, @Param("quantity") int quantity);

    List<OrderStatusVO> selectOrderStatus();

    List<OrderDetailStatusVO> selectDetailStatus();

    List<UserOrderStatusVO> selectUserOrderStatus(String userId);

    List<ProductOrderVO> selectProductOrder();

    Long selectUserSeq(String userId);




}
