package com.andong.smartfactoryweb.mapper;

import com.andong.smartfactoryweb.app.order.vo.*;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
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

    int countOrdersForToday();

    int countNewCustomersForToday();

    int getCountSellMaterial1();

    int getCountSellMaterial2();

    int getCountSellMaterial3();

    Integer countByRegion(String region);

    List<VIPUserVO> selectVIPUser();

    int selectPrice(String dayOfWeek);

    Long getDailySales(Date date);
    Long getTotalSales();

    void minusProduct(@Param("materialSeq") Long materialSeq, @Param("count") int count);

    //int selectMoney(int columnNum);
    List<Integer> selectMoney();
}
