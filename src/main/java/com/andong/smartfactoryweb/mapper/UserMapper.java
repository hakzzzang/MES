package com.andong.smartfactoryweb.mapper;

import com.andong.smartfactoryweb.app.user.vo.LoginVO;
import com.andong.smartfactoryweb.app.user.vo.UserAuthorityVO;
import com.andong.smartfactoryweb.app.user.vo.UserVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {
    public LoginVO findUserByUserId(String userId);

    public int isUserIdExists(String userId);

    public void insertNewUser(UserVO userVO);
    public void insertUserAuthority(UserAuthorityVO userAuthorityVO);

    public List<UserVO> searchAllUsers();
}
