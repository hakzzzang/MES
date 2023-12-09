package com.andong.smartfactoryweb.sample.controller;

import com.andong.smartfactoryweb.config.MQTTConfiguration.OutboundGateway;
import com.andong.smartfactoryweb.sample.service.SampleService;
import com.andong.smartfactoryweb.sample.vo.UserVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
            outboundGateway.sendToMqtt("{\n" +
                    "  \"msg\": \"aaaaaaaaaaaaaaaaa\"\n" +
                    "}", "TEST1");
        }catch(Exception e){
            log.error(e.getMessage());
        }
        return "";
    }



}
