package com.andong.smartfactoryweb.mapper;

import com.andong.smartfactoryweb.app.user.vo.LoginVO;
import com.andong.smartfactoryweb.app.user.vo.UserAuthorityVO;
import com.andong.smartfactoryweb.app.user.vo.UserVO;

import java.util.List;

public interface UserMapper {
    public LoginVO findUserByUserId(String userId);

    public void insertNewUser(UserVO userVO);
    public void insertUserAuthority(UserAuthorityVO userAuthorityVO);

    public List<UserVO> searchAllUsers();
}
