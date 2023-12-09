package com.andong.smartfactoryweb.app.order.service;

import com.andong.smartfactoryweb.app.order.vo.*;

import java.util.Date;
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
    public Long getUserSeq(String userId);

    int countOrdersForToday();

    int countNewCustomersForToday();

    int getCountSellMaterial1();

    int getCountSellMaterial2();

    int getCountSellMaterial3();

    Integer countByRegion(String region);

    List<VIPUserVO> getVIPUser();

    int getPrice(String dayOfWeek);

    List<WeeklyVO> getWeeklyData();

    Long getDailySales(Date date);

    Long getTotalSales();

}
