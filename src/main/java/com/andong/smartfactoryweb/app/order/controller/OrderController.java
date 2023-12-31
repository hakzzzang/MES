package com.andong.smartfactoryweb.app.order.controller;

import com.andong.smartfactoryweb.app.order.service.OrderService;
import com.andong.smartfactoryweb.app.order.vo.*;
import com.andong.smartfactoryweb.app.user.service.UserService;
import com.andong.smartfactoryweb.config.MQTTConfiguration;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/SF")
@RequiredArgsConstructor
@Slf4j
public class OrderController {

    private final OrderService orderService;
    private final UserService userService;
    @Autowired
    private MQTTConfiguration.OutboundGateway outboundGateway;
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

            for (OrderMaterialVO orderMaterialVO : orderMaterials)
            {
                Long materialSeq = orderMaterialVO.getMaterialSeq();
                int count = orderMaterialVO.getCount();
                orderService.minusProduct(materialSeq,count);
            }

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

    @Controller
    public class OrderStatusWebSocketController {

        @MessageMapping("/updateOrderStatus")
        @SendTo("/topic/orderStatusUpdate")
        public String updateOrderStatus(String message) {
            return message;
        }
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

    @GetMapping("/statistics")
    public String StatisticsController(Model model)
    {

        int countOrder = orderService.countOrdersForToday(); // 당일 방문 수 계산

        int countNewCustomers = orderService.countNewCustomersForToday(); // 당일 새로운 고객 수 계산

        /* 부품별 판매 비율 계산 로직 */
        int countSellMaterial1 = orderService.getCountSellMaterial1();
        int countSellMaterial2 = orderService.getCountSellMaterial2();
        int countSellMaterial3 = orderService.getCountSellMaterial3();

        int totalSellCount = countSellMaterial1 + countSellMaterial2 + countSellMaterial3;
        double percentageSellMaterial1 = (countSellMaterial1 / (double)totalSellCount) * 100;
        double percentageSellMaterial2 = (countSellMaterial2 / (double)totalSellCount) * 100;
        double percentageSellMaterial3 = (countSellMaterial3 / (double)totalSellCount) * 100;

        int roundedPercentageSellMaterial1 = (int) Math.round(percentageSellMaterial1);
        int roundedPercentageSellMaterial2 = (int) Math.round(percentageSellMaterial2);
        int roundedPercentageSellMaterial3 = (int) Math.round(percentageSellMaterial3);


        // 지역별 개수 조회
        Integer countCheongju = orderService.countByRegion("청주");
        Integer countIncheon = orderService.countByRegion("인천");
        Integer countUlsan = orderService.countByRegion("울산");

        // VIP User 조회
        List<VIPUserVO> VIPUserList = orderService.getVIPUser();

        // 주간 매출현황

        /*int column0Money = orderService.selectMoney(1);
        int column1Money = orderService.selectMoney(2);
        int column2Money = orderService.selectMoney(3);
        int column3Money = orderService.selectMoney(4);
        int column4Money = orderService.selectMoney(5);
        int column5Money = orderService.selectMoney(6);
        int column6Money = orderService.selectMoney(7);*/

        List<Integer> columnMoney = orderService.selectMoney();
        int column0Money = columnMoney.get(0);
        int column1Money = columnMoney.get(1);
        int column2Money = columnMoney.get(2);
        int column3Money = columnMoney.get(3);
        int column4Money = columnMoney.get(4);
        int column5Money = columnMoney.get(5);
        int column6Money = columnMoney.get(6);

        model.addAttribute("column0Money", column0Money);
        model.addAttribute("column1Money", column1Money);
        model.addAttribute("column2Money", column2Money);
        model.addAttribute("column3Money", column3Money);
        model.addAttribute("column4Money", column4Money);
        model.addAttribute("column5Money", column5Money);
        model.addAttribute("column6Money", column6Money);

        model.addAttribute("VIPUserList", VIPUserList);
        model.addAttribute("countOrder", countOrder);
        model.addAttribute("countNewCustomers", countNewCustomers);
        model.addAttribute("roundedPercentageSellMaterial1", roundedPercentageSellMaterial1);
        model.addAttribute("roundedPercentageSellMaterial2", roundedPercentageSellMaterial2);
        model.addAttribute("roundedPercentageSellMaterial3", roundedPercentageSellMaterial3);
        model.addAttribute("countCheongju", countCheongju);
        model.addAttribute("countIncheon", countIncheon);
        model.addAttribute("countUlsan", countUlsan);


        // 금일 매출액, 총 매출액

        Date todaySales = new Date();
        Long getDailySales = orderService.getDailySales(todaySales);
        Long getTotalSales = orderService.getTotalSales();


        if (getDailySales != null) {
            model.addAttribute("getDailySales", getDailySales);
        } else {
            // 널 처리 또는 기본값 설정
            model.addAttribute("getDailySales", "0$");
        }

        if (getTotalSales != null) {
            model.addAttribute("getTotalSales", getTotalSales);
        } else {
            // 널 처리 또는 기본값 설정
            model.addAttribute("getTotalSales", "0$");
        }

        return "statistics";
    }

    @PostMapping("/mqtt")
    public String mqtt(@RequestBody Map<String, Object> payload) {
        try {
            // orderData가 ArrayList 형태로 전달되었다고 가정
            ArrayList<Map<String, Object>> orderDataList = (ArrayList<Map<String, Object>>) payload.get("orderData");

            // orderDataList를 순회하며 필요한 작업 수행
            for (Map<String, Object> orderData : orderDataList) {
                // 각 orderData에서 필요한 값을 추출
                String purchaseItem = (String) orderData.get("purchaseItem");
                String quantity = (String) orderData.get("quantity");
                String region = (String) orderData.get("region");
                String orderSeq = (String) orderData.get("orderSeq");


                // 추출한 값을 가지고 원하는 작업 수행
                String formattedOrderData = String.format("Purchase Item: %s, Quantity: %s, Region: %s, orderSeq: %s", purchaseItem, quantity, region, orderSeq);
                //String OrderData = String.format(purchaseItem, quantity, region);

                // 원하는 처리를 할 수 있도록 outboundGateway에 전달
                outboundGateway.sendToMqtt(formattedOrderData, "WEB");
                //outboundGateway.sendToMqtt(OrderData, "WEB");
            }
        } catch (Exception e) {
            log.error(e.getMessage());
        }

        // 리다이렉트 시킬 경로를 반환
        return "redirect:/SF/mqtt";
    }

}
