package com.ryl.framework.swagger;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author: ryl
 * @description:
 * @date: 2020-03-07 17:21:51
 */
@Data
@ConfigurationProperties(prefix = "swagger")
@Component
public class SwaggerProperties {

    /**
     * 团队名称
     */
    private String groupName;

    /**
     *扫包路径（扫ApiOperation注解所在的包）
     */
    private String basePackage;

    /**
     *标题
     */
    private String title;

    /**
     *描述
     */
    private String description;

    /**
     *版本
     */
    private String version;

    /**
     *联系人
     */
    private String contactName;

    /**
     *联系网址
     */
    private String contactUrl;

    /**
     *联系邮箱
     */
    private String contactEmail;
}
