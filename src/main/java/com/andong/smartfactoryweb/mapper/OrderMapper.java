package com.andong.smartfactoryweb.mapper;

import com.andong.smartfactoryweb.app.order.vo.MaterialVO;
import com.andong.smartfactoryweb.app.order.vo.OrderDetailStatusVO;
import com.andong.smartfactoryweb.app.order.vo.OrderMaterialVO;
import com.andong.smartfactoryweb.app.order.vo.OrderStatusVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OrderMapper {
    public List<MaterialVO> productInfo();

    public List<OrderMaterialVO> orderInfo();

    void insertOrder(OrderMaterialVO orderMaterialVO);

    void updateMaterialCount(@Param("materialName") String materialName, @Param("quantity") int quantity);

    List<OrderStatusVO> selectOrderStatus();

    List<OrderDetailStatusVO> selectDetailStatus();

}
