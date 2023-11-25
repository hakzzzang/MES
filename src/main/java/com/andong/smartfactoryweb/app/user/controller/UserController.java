package com.andong.smartfactoryweb.app.user.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletResponse;
import java.security.Principal;

@Controller
@RequestMapping("")
@RequiredArgsConstructor
@Slf4j
public class UserController {
    @RequestMapping(value="/login", method = RequestMethod.GET)
    public String login(HttpServletResponse response, Principal principal){

        if(principal == null) {
            return "/login";
        }

        return "redirect:" + "/main";
    }
}
