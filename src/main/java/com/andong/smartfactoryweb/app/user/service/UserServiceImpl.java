package com.andong.smartfactoryweb.app.user.service;

import com.andong.smartfactoryweb.app.user.vo.LoginVO;
import com.andong.smartfactoryweb.app.user.vo.UserAuthorityVO;
import com.andong.smartfactoryweb.app.user.vo.UserVO;
import com.andong.smartfactoryweb.constant.CommonCode;
import com.andong.smartfactoryweb.constant.CommonCode.UserType;
import com.andong.smartfactoryweb.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("userService")
@RequiredArgsConstructor
@Slf4j
@Transactional
public class UserServiceImpl implements UserDetailsService, UserService {

    private final UserMapper userMapper;
    private final PasswordEncoder encoder;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        LoginVO user = userMapper.findUserByUserId(username);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }
        return user;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void signUp(UserVO userVO) {
        try {
            userVO.setPassword(encoder.encode(userVO.getPassword()));
            userMapper.insertNewUser(userVO);

            UserAuthorityVO userAuthorityVO = new UserAuthorityVO();
            userAuthorityVO.setUserSeq(userVO.getUserSeq());
            userAuthorityVO.setCreateId(userVO.getUserId());
            userAuthorityVO.setAuthority(UserType.ROLE_GENERAL.toString());
            this.addUserAuth(userAuthorityVO);

        }catch(Exception e){
            log.error(e.getMessage());
        }

    }

    @Override
    public void addUserAuth(UserAuthorityVO userAuthorityVO) {
        try {
            userMapper.insertUserAuthority(userAuthorityVO);
        }catch (Exception e){
            log.error(e.getMessage());
        }
    }

    @Override
    public List<UserVO> searchAllUsers() {
        return userMapper.searchAllUsers();
    }
}
