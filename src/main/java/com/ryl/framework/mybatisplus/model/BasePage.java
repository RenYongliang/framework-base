package com.ryl.framework.mybatisplus.model;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author: ryl
 * @description:
 * @date: 2020-07-21 16:39:12
 */
@ApiModel("分页基类")
public class BasePage {

    @ApiModelProperty(value = "当前页数", example = "1")
    private int pageIndex;

    @ApiModelProperty(value = "每页条数", example = "20")
    private int pageSize;

    @ApiModelProperty(hidden = true)
    public Page getPage() {
        return new Page((long) pageIndex, (long) pageSize);
    }

    public int getPageIndex() {
        return pageIndex;
    }

    public int getPageSize() {
        return pageSize;
    }

    public BasePage setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
        return this;
    }

    public BasePage setPageSize(int pageSize) {
        this.pageSize = pageSize;
        return this;
    }

    public String toString() {
        return "RequestPage(pageIndex=" + getPageIndex() + ", pageSize=" + getPageSize() + ")";
    }
}
