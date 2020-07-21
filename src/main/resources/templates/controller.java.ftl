package ${package.Controller};

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.web.bind.annotation.RequestMapping;
import ${package.Entity}.${table.entityName};
import ${cfg.ModelDTO}.${table.entityName}DTO;
import ${package.Service}.${table.serviceName};
<#if restControllerStyle>
import org.springframework.web.bind.annotation.RestController;
<#else>
import org.springframework.stereotype.Controller;
</#if>
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ryl.framework.base.ResultModel;
import com.ryl.framework.mybatisplus.model.BasePage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
*
* @description
* @author ${author}
* @since ${date}
*/
@Api(tags = "${table.comment!}")
<#if restControllerStyle>
@RestController
<#else>
@Controller
</#if>
@RequestMapping("/<#if controllerMappingHyphenStyle??>${controllerMappingHyphen}<#else>${table.entityPath}</#if>")
<#if kotlin>
class ${table.controllerName}<#if superControllerClass??> : ${superControllerClass}()</#if>
<#else>
    <#if superControllerClass??>
public class ${table.controllerName} extends ${superControllerClass}<${table.entityName}> {
    <#else>
public class ${table.controllerName} {
    </#if>

    @Autowired
    private I${table.entityName}Service i${table.entityName}Service;

    @ApiOperation(value = "分页查询")
    @PostMapping("/page")
    public ResultModel<IPage<${table.entityName}>> list(@RequestBody BasePage basePage) {
        IPage<${table.entityName}> pageList = i${table.entityName}Service.page(basePage.getPage());
        return ResultModel.success(pageList);
    }


    @ApiOperation(value = "新增或修改${table.comment!}", notes = "新增或修改${table.comment!}信息")
    @PostMapping("/saveOrUpdate")
    public ResultModel<${table.entityName}> saveOrUpdate(@RequestBody @Validated ${table.entityName}DTO <#if controllerMappingHyphenStyle??>${controllerMappingHyphen}<#else>${table.entityPath}</#if>DTO) {
        ${table.entityName} <#if controllerMappingHyphenStyle??>${controllerMappingHyphen}<#else>${table.entityPath}</#if> = new ${table.entityName}();
        BeanUtils.copyProperties(<#if controllerMappingHyphenStyle??>${controllerMappingHyphen}<#else>${table.entityPath}</#if>DTO, <#if controllerMappingHyphenStyle??>${controllerMappingHyphen}<#else>${table.entityPath}</#if>);
        boolean bool = i${table.entityName}Service.saveOrUpdate(<#if controllerMappingHyphenStyle??>${controllerMappingHyphen}<#else>${table.entityPath}</#if>);
        if(bool){
            return ResultModel.success(<#if controllerMappingHyphenStyle??>${controllerMappingHyphen}<#else>${table.entityPath}</#if>);
        }else{
            return ResultModel.fail(bool);
        }
    }

    @ApiOperation(value = "单个或批量删除", notes = "单个或批量删除")
    @PostMapping("/deleteBatch")
    public ResultModel<Boolean> deleteBatch(@RequestBody List<Long> ids) {
        boolean del = i${table.entityName}Service.removeByIds(ids);
        return ResultModel.success(del);
    }

    @ApiOperation(value = "根据id获取${table.comment!}", notes = "根据id获取${table.comment!}")
    @PostMapping("/get")
    public ResultModel<${table.entityName}> get(@RequestBody Long id) {
        ${table.entityName} <#if controllerMappingHyphenStyle??>${controllerMappingHyphen}<#else>${table.entityPath}</#if> = i${table.entityName}Service.getById(id);
        return ResultModel.success(<#if controllerMappingHyphenStyle??>${controllerMappingHyphen}<#else>${table.entityPath}</#if>);
    }

}
</#if>