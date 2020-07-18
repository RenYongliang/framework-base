package com.ryl.framework.mybatisplus.controller;

import com.ryl.framework.base.ResultModel;
import com.ryl.framework.mybatisplus.generator.CodeGenerator;
import com.ryl.framework.mybatisplus.model.CodeGenerateDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author: ryl
 * @description:
 * @date: 2020-07-17 17:40:53
 */
@Api(tags = "代码生成工具")
@RestController
public class GeneratorController {

    @RequestMapping("/generate")
    @ApiOperation("代码自动生成")
    public ResultModel generateCode(@RequestBody CodeGenerateDTO codeGenerateDTO) {
        List<String> tables = codeGenerateDTO.getTables();
        List<String> excludeTables = codeGenerateDTO.getExcludeTables();
        new CodeGenerator().run(codeGenerateDTO.getAuthor(),
                tables.toArray(new String[tables.size()]),
                excludeTables.toArray(new String[excludeTables.size()]));
        return ResultModel.success("代码生成成功！");
    }

}
