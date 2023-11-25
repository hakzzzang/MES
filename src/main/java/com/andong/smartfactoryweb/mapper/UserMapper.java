package com.andong.smartfactoryweb.mapper;

import com.andong.smartfactoryweb.app.user.vo.LoginVO;

public interface UserMapper {
    public LoginVO findUserByUserId(String userId);
}
