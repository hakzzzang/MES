package com.andong.smartfactoryweb.app.user.vo;

import lombok.Data;

import java.util.Date;

@Data
public class UserVO {
    protected Long userSeq;
    protected String userId;
    protected String password;
    protected String userName;
    protected String phoneNumber;
    protected String email;
    protected String region;
    protected Date createAt;
    protected String createId;
    protected Date updateAt;
    protected String updateId;
}
