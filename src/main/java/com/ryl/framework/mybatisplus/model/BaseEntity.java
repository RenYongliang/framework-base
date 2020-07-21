package com.ryl.framework.mybatisplus.model;

import com.baomidou.mybatisplus.annotation.TableLogic;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author: ryl
 * @description:
 * @date: 2020-07-17 17:22:02
 */
@Data
public class BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("创建人id")
    private Long createUserId;

    @ApiModelProperty("更新人id")
    private Long modifyUserId;

    @ApiModelProperty("创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty("更新时间")
    private LocalDateTime modifyTime;

    @TableLogic
    @ApiModelProperty("删除状态")
    private Integer stateDeleted;
}
