package com.andong.smartfactoryweb.sample.controller;

import com.andong.smartfactoryweb.config.MQTTConfiguration.OutboundGateway;
import com.andong.smartfactoryweb.sample.service.SampleService;
import com.andong.smartfactoryweb.sample.vo.UserVO;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@Controller
@RequestMapping("/sample")
@Slf4j
@RequiredArgsConstructor
public class SampleController {
    private final SampleService sampleService;
    @Autowired
    private OutboundGateway outboundGateway;
    @GetMapping("/getMapping")
    public String SampelGetMappingTest(/*@RequestParam String param, @RequestParam String param2, Model model*/) {
        // model.addAttribute("param1", param);
        // model.addAttribute("param2", param2);
        UserVO user = sampleService.getSample();
        return "/success";
    }

    @GetMapping("/login")
    public String LoginController() {
        return "login";
    }

    @GetMapping("/signup")
    public String SignupController(){
        return "signup";
    }

    @GetMapping("/main")
    public String MainController(){
        return "main";
    }

    @GetMapping("/order")
    public String OrderController(){
        return "order";
    }

    @GetMapping("/orderstatus")
    public String OrderstatusController(){
        return "orderstatus";
    }

    @GetMapping("/inventory")
    public String InventoryController(){
        return "inventory";
    }

    @GetMapping("/statistics")
    public String StatisticsController(){
        return "statistics";
    }

    @GetMapping("/detail")
    public String DetailController(){
        return "detail";
    }

    @GetMapping("/mqtt")
    public String mqtt(){
        try {
            outboundGateway.sendToMqtt("MQTT TEST SEND", "WEB");
        }catch(Exception e){
            log.error(e.getMessage());
        }
        return "";
    }

    @GetMapping("/json")
    public String json(){
        try {
            //1. 객체 to Json 문자열 변환
            UserVO vo = new UserVO();
            vo.setUserSeq(10L);
            vo.setEmailAddress("smartfactory@gmail.com");
            vo.setPhomeNumber("010-1234-4567");
            vo.setUserId("smartFactory");
            vo.setUserType("ROLE_ADMIN");
            vo.setCreateAt(new Date());


            ObjectMapper objectMapper = new ObjectMapper();
            String jsonValue = objectMapper.writeValueAsString(vo);

            //2. json형태의 문자열을 Object로 변환

            UserVO vo2 = objectMapper.readValue(jsonValue, UserVO.class);
            log.debug("");
        }catch(Exception e){
            log.error(e.getMessage());
        }
        return "";
    }
}
