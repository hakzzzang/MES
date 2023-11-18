package com.andong.smartfactoryweb.sample.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest")
@Slf4j
public class RestSampleController {
    @GetMapping("/getMapping")
    public String SampelGetMappingTest(){
        return "success";
    }
}
