package com.andong.smartfactoryweb.app.user.service;

import com.andong.smartfactoryweb.app.user.vo.LoginVO;
import com.andong.smartfactoryweb.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service("userService")
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserDetailsService {
    private final UserMapper userMapper;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        LoginVO user = userMapper.findUserByUserId(username);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }
        return user;
    }
}
