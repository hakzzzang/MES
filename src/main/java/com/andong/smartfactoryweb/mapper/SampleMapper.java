package com.andong.smartfactoryweb.mapper;

import com.andong.smartfactoryweb.sample.vo.UserVO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SampleMapper {
    UserVO getUser(String userId);
}
