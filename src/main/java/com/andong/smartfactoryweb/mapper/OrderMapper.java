package com.andong.smartfactoryweb.mapper;

import com.andong.smartfactoryweb.app.order.vo.MaterialVO;
import com.andong.smartfactoryweb.app.order.vo.OrderMaterialVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OrderMapper {
    public List<MaterialVO> productCount();

    public List<OrderMaterialVO> productOrder();

    void insertOrder(OrderMaterialVO orderMaterialVO);

    void updateMaterialCount(@Param("materialName") String materialName, @Param("quantity") int quantity);
}
