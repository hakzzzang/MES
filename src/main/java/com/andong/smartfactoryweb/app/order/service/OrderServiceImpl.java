package com.andong.smartfactoryweb.app.order.service;

import com.andong.smartfactoryweb.app.order.vo.MaterialVO;
import com.andong.smartfactoryweb.app.order.vo.OrderDetailStatusVO;
import com.andong.smartfactoryweb.app.order.vo.OrderMaterialVO;
import com.andong.smartfactoryweb.app.order.vo.OrderStatusVO;
import com.andong.smartfactoryweb.mapper.OrderMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
    public List<MaterialVO> productCount() {
        return orderMapper.productCount();
    }

    @Override
    public List<OrderMaterialVO> productOrder() {
        return orderMapper.productOrder();
    }

    @Override
    public void saveOrders(List<OrderMaterialVO> orderMaterials) {
        for (OrderMaterialVO orderMaterialVO : orderMaterials) {
            // OrderMapper를 통해 데이터베이스에 주문 정보를 저장하는 메서드를 호출
            orderMapper.insertOrder(orderMaterialVO);
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
}

