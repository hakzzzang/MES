package com.andong.smartfactoryweb.app.order.controller;

import com.andong.smartfactoryweb.app.order.service.OrderService;
import com.andong.smartfactoryweb.app.order.vo.MaterialVO;
import com.andong.smartfactoryweb.app.order.vo.OrderMaterialVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.cache.spi.support.AbstractReadWriteAccess;
import org.hibernate.cache.spi.support.AbstractReadWriteAccess.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/SF")
@RequiredArgsConstructor
@Slf4j
public class OrderController {

    private final OrderService orderService;
    @RequestMapping(value = "/inventory" ,method = RequestMethod.GET)
    public String product(Model model){
        List<MaterialVO> material = orderService.productCount();
        model.addAttribute("material", material);
        return "inventory";
    }

    @RequestMapping(value = "/inventoryorder", method = RequestMethod.POST)
    public String orderMaterial(@RequestParam("materialName") String materialName, @RequestParam("quantity") int quantity) {
        // 발주 요청이 들어왔을 때 서비스를 호출
        orderService.orderMaterial(materialName, quantity);
        return "inventory"; // 적절한 리다이렉션 경로로 변경 // 에러
    }

    @RequestMapping(value = "/order", method = RequestMethod.GET)
    public String productOrder(Model model){
        List<MaterialVO> material = orderService.productCount();
        List<OrderMaterialVO> orderMaterial = orderService.productOrder();
        model.addAttribute("order", orderMaterial);
        model.addAttribute("material", material);
        model.addAttribute("Fanprice", 900000);
        model.addAttribute("Gearprice", 1300000);
        model.addAttribute("Highprice", 1750000);
        return "order";
    }
    @RequestMapping(value = "/placeOrder", method = RequestMethod.POST)
    public ResponseEntity<String> placeOrder(@RequestBody List<OrderMaterialVO> orderMaterials) {
        try {
            orderService.saveOrders(orderMaterials);
            return ResponseEntity.ok("주문이 성공적으로 완료되었습니다.");
        } catch (Exception e) {
            log.error("주문 실패. 다시 시도하세요.", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("주문 실패. 다시 시도하세요.");
        }
    }
}
