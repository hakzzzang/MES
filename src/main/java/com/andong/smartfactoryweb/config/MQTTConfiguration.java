package com.andong.smartfactoryweb.config;

import com.andong.smartfactoryweb.app.order.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.paho.client.mqttv3.MqttAsyncClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.core.MessageProducer;
import org.springframework.integration.mqtt.core.DefaultMqttPahoClientFactory;
import org.springframework.integration.mqtt.inbound.MqttPahoMessageDrivenChannelAdapter;
import org.springframework.integration.mqtt.outbound.MqttPahoMessageHandler;
import org.springframework.integration.mqtt.support.DefaultPahoMessageConverter;
import org.springframework.integration.mqtt.support.MqttHeaders;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHandler;
import org.springframework.messaging.handler.annotation.Header;

@Configuration
@Slf4j
public class MQTTConfiguration {
    @Autowired
    OrderService orderService;

    @Autowired
    private OrderStatusPublisher orderStatusPublisher;
    private static final String MQTT_USERNAME = "haksu";
    private static final String MQTT_PASSWORD = "1234";

    private static final String BROKER_URL = "tcp://222.106.31.248:1883";
    private static final String MQTT_PUB_CLIENT_ID = MqttAsyncClient.generateClientId();
    private static final String MQTT_SUB_CLIENT_ID = MqttAsyncClient.generateClientId();

    private MqttConnectOptions connectOptions() {
        MqttConnectOptions options = new MqttConnectOptions();
        options.setCleanSession(true);
        // options.setUserName(MQTT_USERNAME);
        // options.setPassword(MQTT_PASSWORD.toCharArray());
        return options;
    }

    @Bean
    public DefaultMqttPahoClientFactory defaultMqttPahoClientFactory() {
        DefaultMqttPahoClientFactory clientFactory = new DefaultMqttPahoClientFactory();
        clientFactory.setConnectionOptions(connectOptions());
        return clientFactory;
    }

    @Bean
    public MessageChannel mqttInputChannel() {
        return new DirectChannel();
    }

    @Bean
    public MessageProducer inboundChannel() {
        MqttPahoMessageDrivenChannelAdapter adapter =
                new MqttPahoMessageDrivenChannelAdapter(BROKER_URL, MQTT_SUB_CLIENT_ID,
                        "WEB");
        adapter.setCompletionTimeout(5000);
        adapter.setConverter(new DefaultPahoMessageConverter());
        adapter.setQos(1);
        adapter.setOutputChannel(mqttInputChannel());
        return adapter;
    }

    @Bean
    @ServiceActivator(inputChannel = "mqttInputChannel")
    public MessageHandler inboundMessageHandler() {
        return message -> {
            String topic = (String) message.getHeaders().get(MqttHeaders.RECEIVED_TOPIC);
            log.info("Topic:" + topic);
            log.info("Payload: " + message.getPayload());
            // Assuming that the payload is a String representing the orderSeq
            String orderSeqStr = (String) message.getPayload();


            try {
                int orderSeq = Integer.parseInt(orderSeqStr);
                orderService.updateProductStatus(orderSeq);
                orderStatusPublisher.publishOrderStatusUpdate(orderSeq); // 웹 소켓을 통해 '생산완료' 메시지 전송
                log.info("OrderSeq: " + orderSeq);
            } catch (NumberFormatException e) {
                log.error("Failed to parse OrderSeq as an integer: " + orderSeqStr, e);
                // Handle the error as needed
            }

        };
    }

    @Bean
    @ServiceActivator(inputChannel = "mqttOutboundChannel")
    public MessageHandler mqttOutbound() {
        MqttPahoMessageHandler messageHandler =
                new MqttPahoMessageHandler(BROKER_URL, MQTT_PUB_CLIENT_ID, defaultMqttPahoClientFactory());
        messageHandler.setAsync(true);
        messageHandler.setDefaultTopic("WEB");
        messageHandler.setDefaultQos(1);
        return messageHandler;
    }
    @Bean
    public MessageChannel mqttOutboundChannel() {
        return new DirectChannel();
    }

    @MessagingGateway(defaultRequestChannel = "mqttOutboundChannel")
    public interface OutboundGateway {
        void sendToMqtt(String payload, @Header(MqttHeaders.TOPIC) String topic);
    }
}
