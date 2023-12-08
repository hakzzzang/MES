package com.andong.smartfactoryweb.app.order.service;

import com.andong.smartfactoryweb.app.order.vo.*;

import java.util.List;

public interface OrderService {
    List<MaterialVO> productInfo();

    List<OrderMaterialVO> orderInfo();

    void saveOrders(ProductOrderVO productOrderVO, List<OrderMaterialVO> orderMaterials);

    public void orderMaterial(String materialName, int quantity);

    List<OrderStatusVO> getOrderStatus();

    public List<OrderDetailStatusVO> getDetailStatus();


    List<UserOrderStatusVO> getUserOrderStatus(String userId);


    List<ProductOrderVO> getProductOrders();

}
