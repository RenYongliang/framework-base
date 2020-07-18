package com.ryl.framework.mybatisplus.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @author: ryl
 * @description:
 * @date: 2020-07-18 13:15:43
 */
@Data
public class CodeGenerateDTO {

    @ApiModelProperty("作者")
    private String author;

    @ApiModelProperty("要生成的表名列表")
    private List<String> tables;

    @ApiModelProperty("要排除的表名列表")
    private List<String> excludeTables;
}
