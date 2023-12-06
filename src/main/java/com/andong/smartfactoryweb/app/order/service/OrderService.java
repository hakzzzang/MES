package com.andong.smartfactoryweb.app.order.service;

import com.andong.smartfactoryweb.app.order.vo.MaterialVO;
import com.andong.smartfactoryweb.app.order.vo.OrderMaterialVO;

import java.util.List;

public interface OrderService {
    List<MaterialVO> productCount();

    List<OrderMaterialVO> productOrder();

    void saveOrders(List<OrderMaterialVO> orderMaterials); // 주문저장

    public void orderMaterial(String materialName, int quantity);


}
