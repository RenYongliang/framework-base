package com.ryl.framework.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: ryl
 * @description: JWT token 工具类
 * @date: 2020-03-03 09:21:07
 */
public class JwtUtil {

    @Autowired
    private static JwtProperties jwtProperties;

    /**
     * 传userId生成token
     * @param userGuid
     * @return
     */
    public static String generateJwtToken(String userGuid) {
        JwtUser jwtUser = new JwtUser();
        jwtUser.setUserId(userGuid);
        return generateJwtToken(jwtUser);
    }

    /**
     * 传jwtUser生成token
     * @param jwtUser
     * @return
     */
    public static String generateJwtToken(JwtUser jwtUser) {
        // expireDate 2小时
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.HOUR, jwtProperties.getExpireTime());
        Date expireDate = calendar.getTime();
        //载荷信息 存放user对象
        Map<String, Object> claimsMap = new HashMap<>(1);
        claimsMap.put(jwtProperties.claimsKey, jwtUser);
        String jwt = Jwts.builder()
                .setClaims(claimsMap)
                .setSubject(jwtProperties.subject)
                .setExpiration(expireDate)
                .signWith(SignatureAlgorithm.HS512, jwtProperties.getSecret())
                .compact();
        return jwt;
    }

    /**
     * 传httpRequest获取jwtUser
     * @param request
     * @return
     */
    public static JwtUser getJwtUser(HttpServletRequest request){
        //header获取token
        String token = request.getHeader(jwtProperties.getHeaderKey());
        return getJwtUser(token);
    }

    /**
     * 传token获取jwtUser
     * @param token
     * @return
     */
    public static JwtUser getJwtUser(String token){
        //去除前缀 Bearer
        token = token.substring(jwtProperties.tokenPrefix.length());
        Object o = Jwts.parser()
                .setSigningKey(jwtProperties.getSecret())
                .parseClaimsJws(token)
                .getBody()
                .get(jwtProperties.getClaimsKey());
        //转成JwtUser对象
        return new ObjectMapper().convertValue(o,JwtUser.class);
    }

}
