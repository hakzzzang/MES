package com.andong.smartfactoryweb.sample.controller;

import com.andong.smartfactoryweb.sample.service.SampleService;
import com.andong.smartfactoryweb.sample.vo.UserVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/sample")
@Slf4j
@RequiredArgsConstructor
public class SampleController {
    private final SampleService sampleService;
    @GetMapping("/getMapping")
    public String SampelGetMappingTest(/*@RequestParam String param, @RequestParam String param2, Model model*/) {
       // model.addAttribute("param1", param);
       // model.addAttribute("param2", param2);
        UserVO user = sampleService.getSample();
        return "/success";
    }

    @GetMapping("/get")
    public void SampleGet(){
    }

}
