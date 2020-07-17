package com.ryl.framework.jwt;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author: ryl
 * @description: jwt参数配置类
 * @date: 2020-03-03 09:26:39
 */
@Data
@Component
@ConfigurationProperties(prefix = "jwt")
public class JwtProperties {

    //存放Token的Header Key
    public String headerKey;

    //密匙key
    public String secret;

    //过期时间 2小时
    public Integer expireTime;

    //header token 前缀
    public String tokenPrefix;

    //playLoad 载荷信息KEY
    public String claimsKey ;

    //JWT主体信息 区分微信端和管理端
    public String subject;

    //JWT白名单
    public String whiteList;

}
