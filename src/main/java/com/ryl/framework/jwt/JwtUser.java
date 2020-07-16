package com.ryl.framework.jwt;

import lombok.Data;

/**
 * @author: ryl
 * @description: token载荷中存放的user对象，可在此添加任意可用信息
 * @date: 2020-03-03 09:35:23
 */
@Data
public class JwtUser {

    /**
     * 用户id
     */
    private String userId;

    /**
     * 用户姓名
     */
    private String username;

}
