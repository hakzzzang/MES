package com.andong.smartfactoryweb.app.user.controller;

import com.andong.smartfactoryweb.app.user.service.UserService;
import com.andong.smartfactoryweb.app.user.vo.UserVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/SF")
@RequiredArgsConstructor
@Slf4j
public class UserController {
    private final UserService userService;
    @RequestMapping(value = "/login", method = {RequestMethod.POST, RequestMethod.GET})
    public String login(HttpServletRequest request, HttpServletResponse response) {
        String userId = request.getParameter("username");
        String password = request.getParameter("password");

        // DB에서 사용자 정보 가져오기
        UserVO user = userService.findUserByUserId(userId);

        //if (user != null && password.equals(user.getPassword())) {
        if(user != null){
            // 비밀번호 일치: 로그인 성공
            log.info("로그인 성공: {}", userId);

            // 세션에 사용자 정보 추가
            request.getSession().setAttribute("user", user);
            log.info("세션에 저장된 user: {}", request.getSession().getAttribute("user"));


            // 여기에 성공 시의 처리를 추가하면 됩니다.

            return "main";
        } else {
            // 비밀번호 불일치 또는 사용자가 존재하지 않음: 로그인 실패
            log.info("로그인 실패: {}", userId);

//            // 실패 시의 처리를 추가하거나 에러 메시지를 표시할 수 있습니다.
//            return "redirect:/SF/login?error";
            return "login";
        }
    }

    @RequestMapping(value="/signup", method = RequestMethod.POST)//POSTMAPPING
    public String signUp(UserVO userVO){
        userService.signUp(userVO);
        return "signup";
    }

    @GetMapping("/signup")
    public String SignupController(){
        return "signup";
    }

    @RequestMapping(value="/users", method = RequestMethod.GET)
    public String getUsers(Model model){
        List<UserVO> users = userService.searchAllUsers();
        log.debug("");
        model.addAttribute("users", users);
        return "main";
    }
//
    // 로그아웃을 처리하는 메서드
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        // 세션을 무효화하여 사용자 정보를 삭제
        session.removeAttribute("user");


        // 로그아웃 후 리다이렉트할 경로를 지정 (예: 메인 페이지로 리다이렉트)
        return "redirect:/SF/main";
    }

    @GetMapping("/detail")
    public String DetailController(){
        return "detail";
    }

    @GetMapping("/main")
    public String MainController(){
        return "main";
    }

    @GetMapping("/orderstatus")
    public String OrderstatusController(){
        return "orderstatus";
    }

    @GetMapping("/statistics")
    public String StatisticsController(){
        return "statistics";
    }
}