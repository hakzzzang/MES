package com.andong.smartfactoryweb.sample.service;

import com.andong.smartfactoryweb.mapper.SampleMapper;
import com.andong.smartfactoryweb.sample.vo.UserVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service("sampleService")
@RequiredArgsConstructor
@Slf4j
public class SampleServiceImpl implements SampleService{
    private final SampleMapper sampleMapper;
    public UserVO getSample(){
        try {
            UserVO user = sampleMapper.getUser("dooho");
            return user;
        }catch(Exception e){
            log.error(e.getMessage());
            throw e;
        }
    }
}
