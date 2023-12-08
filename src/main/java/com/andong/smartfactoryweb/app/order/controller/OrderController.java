package com.andong.smartfactoryweb.app.order.controller;

import com.andong.smartfactoryweb.app.order.service.OrderService;
import com.andong.smartfactoryweb.app.order.vo.*;
import com.andong.smartfactoryweb.app.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/SF")
@RequiredArgsConstructor
@Slf4j
public class OrderController {

    private final OrderService orderService;
    private final UserService userService;
    @RequestMapping(value = "/inventory" ,method = RequestMethod.GET)
    public String product(Model model){
        List<MaterialVO> material = orderService.productInfo();
        model.addAttribute("material", material);
        return "inventory";
    }

    @RequestMapping(value = "/inventoryorder", method = RequestMethod.POST)
    public String orderMaterial(@RequestParam("materialName") String materialName, @RequestParam("quantity") int quantity) {
        // 발주 요청이 들어왔을 때 서비스를 호출
        orderService.orderMaterial(materialName, quantity);
        return "inventory";
    }

    @RequestMapping(value = "/order", method = RequestMethod.GET)
    public String productOrder(Model model){
        List<MaterialVO> material = orderService.productInfo();
        model.addAttribute("material", material);

        List<OrderMaterialVO> orderMaterial = orderService.orderInfo();
        model.addAttribute("order", orderMaterial);

        List<ProductOrderVO> productOrders = orderService.getProductOrders();
        model.addAttribute("productOrders", productOrders);


        model.addAttribute("Fanprice", 900000);
        model.addAttribute("Gearprice", 1300000);
        model.addAttribute("Highprice", 1750000);
        return "order";
    }
    @RequestMapping(value = "/placeOrder", method = RequestMethod.POST)
    public ResponseEntity<String> placeOrder(@RequestBody List<OrderMaterialVO> orderMaterials ,ProductOrderVO productOrderVO, Principal principal) {
        productOrderVO.setProductStatus("수주");
        String userId = principal.getName();
        Long userSeq = orderService.getUserSeq(userId);
        productOrderVO.setUserSeq(userSeq);
        try {
            orderService.saveOrders(productOrderVO, orderMaterials);
            return ResponseEntity.ok("주문이 성공적으로 완료되었습니다.");
        } catch (Exception e) {
            log.error("주문 실패. 다시 시도하세요.", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("주문 실패. 다시 시도하세요.");
        }
    }

    @GetMapping("/orderstatus")
    public String OrderStatusController(Model model) {
        List<OrderStatusVO> orderStatusList = orderService.getOrderStatus();
        List<OrderDetailStatusVO> orderDetailStatusList = orderService.getDetailStatus();
        model.addAttribute("orderStatusList", orderStatusList);
        model.addAttribute("orderDetailStatusList", orderDetailStatusList);

        return "orderstatus";
    }

    @GetMapping("/userorderstatus")
    public String UserOrderStatusController(Model model, Principal principal) {
        String userId = principal.getName();

        List<UserOrderStatusVO> userOrderStatusList = orderService.getUserOrderStatus(userId);
        List<OrderDetailStatusVO> orderDetailStatusList = orderService.getDetailStatus();

        model.addAttribute("userOrderStatusList", userOrderStatusList);
        model.addAttribute("orderDetailStatusList", orderDetailStatusList);



        return "userorderstatus";
    }
}
