package com.andong.smartfactoryweb.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

@Component
public class OrderStatusPublisher {


    private final SimpMessagingTemplate messagingTemplate;

    @Autowired
    public OrderStatusPublisher(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    public void publishOrderStatusUpdate(int orderSeq) {
        // 생산완료 메시지를 웹 소켓을 통해 전송
        messagingTemplate.convertAndSend("/topic/orderStatusUpdate", "생산완료 - 주문 번호: " + orderSeq);
    }

}
