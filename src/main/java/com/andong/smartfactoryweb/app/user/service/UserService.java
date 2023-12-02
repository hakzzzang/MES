package com.andong.smartfactoryweb.app.user.service;

import com.andong.smartfactoryweb.app.user.vo.UserAuthorityVO;
import com.andong.smartfactoryweb.app.user.vo.UserVO;

import java.util.List;

public interface UserService {
    void signUp(UserVO userVO);
    void addUserAuth(UserAuthorityVO userAuthorityVO);

    List<UserVO> searchAllUsers();
}
