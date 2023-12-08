package com.andong.smartfactoryweb.app.order.service;

import com.andong.smartfactoryweb.app.order.vo.*;
import com.andong.smartfactoryweb.mapper.OrderMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.criterion.Order;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("OrderService")
@RequiredArgsConstructor
@Slf4j
@Transactional

public class OrderServiceImpl implements OrderService {
    private final OrderMapper orderMapper;

    @Override
    public List<MaterialVO> productInfo() {
        return orderMapper.productInfo();
    }

    @Override
    public List<OrderMaterialVO> orderInfo() {
        return orderMapper.orderInfo();
    }

    @Transactional
    public void saveOrders(ProductOrderVO productOrderVO, List<OrderMaterialVO> orderMaterials) {
        // product_order_seq를 먼저 삽입
        orderMapper.insertProductOrder(productOrderVO);

        // 삽입된 product_order_seq를 가져와서 orderMaterials에 설정
        Long productOrderSeq = productOrderVO.getProductOrderSeq();
        for (OrderMaterialVO orderMaterialVO : orderMaterials) {
            orderMaterialVO.setProductOrderSeq(productOrderSeq);
            orderMapper.insertOrderMaterial(orderMaterialVO);
        }
    }

    @Transactional
    public void orderMaterial(String materialName, int quantity) {
        // 발주가 들어왔을 때 부품의 개수를 증가시키는 비즈니스 로직
        orderMapper.updateMaterialCount(materialName, quantity);
    }

    @Override
    public List<OrderStatusVO> getOrderStatus()
    {
        return orderMapper.selectOrderStatus();
    }

    public List<OrderDetailStatusVO> getDetailStatus() {
        return orderMapper.selectDetailStatus();
    }

    public List<UserOrderStatusVO> getUserOrderStatus(String userId)
    {
        return orderMapper.selectUserOrderStatus(userId);
    }

    @Override
    public List<ProductOrderVO> getProductOrders() {
        return orderMapper.selectProductOrder();
    }

}

