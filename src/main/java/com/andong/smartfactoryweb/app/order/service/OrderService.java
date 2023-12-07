package com.andong.smartfactoryweb.app.order.service;

import com.andong.smartfactoryweb.app.order.vo.MaterialVO;
import com.andong.smartfactoryweb.app.order.vo.OrderDetailStatusVO;
import com.andong.smartfactoryweb.app.order.vo.OrderMaterialVO;
import com.andong.smartfactoryweb.app.order.vo.OrderStatusVO;

import java.util.List;

public interface OrderService {
    List<MaterialVO> productInfo();

    List<OrderMaterialVO> orderInfo();

    void saveOrders(List<OrderMaterialVO> orderMaterials); // 주문저장

    public void orderMaterial(String materialName, int quantity);

    List<OrderStatusVO> getOrderStatus();

    public List<OrderDetailStatusVO> getDetailStatus();

}
