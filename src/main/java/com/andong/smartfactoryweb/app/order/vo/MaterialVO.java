package com.andong.smartfactoryweb.app.order.vo;

import com.andong.smartfactoryweb.app.user.vo.UserAuthorityVO;
import lombok.Data;
import org.apache.catalina.startup.UserDatabase;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

@Data
public class MaterialVO {
    private Long materialSeq;
    private String materialName;
    private Long materialRemainCount;
    private Long materialPrice;
    private List<OrderMaterialVO> orderMaterials;


}
