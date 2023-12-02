package com.andong.smartfactoryweb.app.user.controller;

import com.andong.smartfactoryweb.app.user.service.UserService;
import com.andong.smartfactoryweb.app.user.vo.UserVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletResponse;
import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
@Slf4j
public class UserController {
    private final UserService userService;
    @RequestMapping(value="/login", method = RequestMethod.GET)
    public String login(HttpServletResponse response, Principal principal){

        if(principal == null) {
            return "/login";
        }

        return "redirect:" + "/main";
    }

    @RequestMapping(value="/sign-up", method = RequestMethod.POST)
    public String signUp(UserVO userVO){
        userService.signUp(userVO);
        return "/";
    }

    @RequestMapping(value="/users", method = RequestMethod.GET)
    public String getUsers(){
        List<UserVO> users = userService.searchAllUsers();
        log.debug("");
        return "/main";
    }
}
