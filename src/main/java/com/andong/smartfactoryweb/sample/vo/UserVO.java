package com.andong.smartfactoryweb.sample.vo;

import lombok.Data;

import java.util.Date;

@Data
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
