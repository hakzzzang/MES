package com.andong.smartfactoryweb.app.user.vo;

import lombok.Data;

import java.util.Date;

@Data
public class UserAuthorityVO {
    private Long authoritySeq;
    private Long userSeq;
    private String authority;
    private Date createAt;
    private String createId;
}