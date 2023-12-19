package com.andong.smartfactoryweb.app.order.service;

import com.andong.smartfactoryweb.app.order.vo.*;
import com.andong.smartfactoryweb.mapper.OrderMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.hibernate.criterion.Order;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    public Long getUserSeq(String userId)
    {
        return orderMapper.selectUserSeq(userId);
    }

    public int countOrdersForToday()
    {
        return orderMapper.countOrdersForToday();
    }

    public int countNewCustomersForToday()
    {
        return orderMapper.countNewCustomersForToday();
    }

    public int getCountSellMaterial1()
    {
        return orderMapper.getCountSellMaterial1();
    }

    public int getCountSellMaterial2()
    {
        return orderMapper.getCountSellMaterial2();
    }

    public int getCountSellMaterial3()
    {
        return orderMapper.getCountSellMaterial3();
    }

    public Integer countByRegion(String region) {
        return orderMapper.countByRegion(region);
    }

    public List<VIPUserVO> getVIPUser()
    {
        return orderMapper.selectVIPUser();
    }

    public int getPrice(String dayOfWeek)
    {
        return orderMapper.selectPrice(dayOfWeek);
    }

    @Override
    public Long getDailySales(Date date){
        return orderMapper.getDailySales(date);
    }

    @Override
    public Long getTotalSales(){
        return orderMapper.getTotalSales();
    }

    public void minusProduct(Long materialSeq, int count) {
        orderMapper.minusProduct(materialSeq, count);
    }

    /*public int selectMoney(int columnNum)
    {
        return orderMapper.selectMoney(columnNum);
    }*/
    public List<Integer> selectMoney(){
        return orderMapper.selectMoney();
    }

    public void updateProductStatus(int orderSeq) {
        orderMapper.updateProductStatus(orderSeq);
    }



}

