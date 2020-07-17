package com.ryl.framework.jwt;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author: ryl
 * @description: jwt参数配置类
 * @date: 2020-03-03 09:26:39
 */
@Component
public class JwtProperties {

    /**
     *  存放Token的Header Key
     */
    public static String HEADER_KEY;

    /**
     * 密匙key
     */
    public static  String SECRET;

    /**
     * 过期时间 2小时
     */
    public static int EXPIRE_TIME;

    /**
     * header token 前缀
     */
    public static String TOKEN_PREFIX_TYPE;

    /**
     * playLoad 载荷信息KEY
     */
    public static String CLAIMS_KEY ;

    /**
     * JWT主体信息 区分微信端和管理端
     */
    public static String JWT_SUBJECT;

    /**
     * JWT白名单
     */
    public static String JWT_WHITE_LIST;


    @Value("${jwt.header-key:Authorization}")
    public void setHeaderKey(String headerKey){
        HEADER_KEY = headerKey;
    }

    @Value("${jwt.secret:3306wdnss^rylCoder}")
    public void setSecret(String secret){
        SECRET = secret;
    }

    @Value("${jwt.expire-time:2}")
    public void setExpireTime(int expireTime){
        EXPIRE_TIME = expireTime;
    }

    @Value("${jwt.token-prefix-type:Bearer }")
    public void setTokenPrefixType(String tokenPrefixType){
        TOKEN_PREFIX_TYPE = tokenPrefixType;
    }

    @Value("${jwt.claims-key:user}")
    public void setClaimsKey(String claimsKey){
        CLAIMS_KEY = claimsKey;
    }

    @Value("${jwt.jwt-subject:ryl_jwt_subject}")
    public void setJwtSubject(String jwtSubject){
        JWT_SUBJECT = jwtSubject;
    }

    @Value("${jwt.white-list:/user/login;}")
    public void setJwtWhiteList(String jwtWhiteList){
        JWT_WHITE_LIST = jwtWhiteList;
    }
}
