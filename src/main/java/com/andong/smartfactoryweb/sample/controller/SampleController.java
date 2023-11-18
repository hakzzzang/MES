package com.andong.smartfactoryweb.sample.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/sample")
@Slf4j
public class SampleController {
    @GetMapping("/getMapping")
    public String SampelGetMappingTest(@RequestParam String param){
        log.debug("SampelGetMappingTest");
        log.debug("SampelGetMappingTest");
        log.debug("SampelGetMappingTest");

        // 안녕
        // 하세요
        // Test

        return "success";
    }

    @GetMapping("/get")
    public void SampleGet(){
        log.debug("GETGETGET");
        log.debug("GETGETGET");
        log.debug("GETGETGET");
    }

}
