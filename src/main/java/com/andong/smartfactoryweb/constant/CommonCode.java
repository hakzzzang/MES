package com.andong.smartfactoryweb.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

public class CommonCode {
    @Getter
    @AllArgsConstructor
    public enum UserType{
        ROLE_ADMIN("관리자"), ROLE_GENERAL("일반유저");
        private String desc;
    }
}
