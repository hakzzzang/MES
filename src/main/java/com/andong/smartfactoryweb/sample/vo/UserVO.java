package com.andong.smartfactoryweb.sample.vo;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class UserVO {
    private Long userSeq;
    private String userId;
    private String userType;
    private String userName;
    private String phomeNumber;
    private String emailAddress;
    private Date createAt;
    private String createId;

}
