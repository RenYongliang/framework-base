package ${package.Controller};

import org.springframework.web.bind.annotation.RequestMapping;
import ${package.Entity}.${table.entityName};
import ${cfg.ModelDTO}.${table.entityName}DTO;
import ${package.Service}.${table.serviceName};
<#if restControllerStyle>
import org.springframework.web.bind.annotation.RestController;
<#else>
import org.springframework.stereotype.Controller;
</#if>
<#if superControllerClassPackage??>
import org.springframework.web.bind.annotation.PostMapping;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiOperation;
import com.ryl.framework.base.ResultModel;
import com.ryl.framework.base.ResultStatus;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ryl.framework.mybatisplus.model.BaseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.validation.annotation.Validated;
import ${superControllerClassPackage};
import java.util.List;
</#if>

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
@RequestMapping("<#if package.ModuleName??>/${package.ModuleName?replace('.','/')}</#if>/<#if controllerMappingHyphenStyle??>${controllerMappingHyphen}<#else>${table.entityPath}</#if>")
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
    public ResultModel<IPage<${table.entityName}>> list(@RequestBody Page page) {
        IPage<${table.entityName}> pageList = i${table.entityName}Service.page(page);
        return ResultModel.success(pageList);
    }


    @ApiOperation(value = "新增或修改${table.comment!}", notes = "新增或修改${table.comment!}信息")
    @PostMapping("/saveOrUpdate")
    public ResultModel<${table.entityName}> saveOrUpdate(@RequestBody @Validated ${table.entityName}DTO <#if controllerMappingHyphenStyle??>${controllerMappingHyphen}<#else>${table.entityPath}</#if>DTO) {
    ${table.entityName} <#if controllerMappingHyphenStyle??>${controllerMappingHyphen}<#else>${table.entityPath}</#if> = BeanUtils.copyProperties(<#if controllerMappingHyphenStyle??>${controllerMappingHyphen}<#else>${table.entityPath}</#if>DTO,<#if controllerMappingHyphenStyle??>${controllerMappingHyphen}<#else>${table.entityPath}</#if>DTO);
        boolean bool = i${table.entityName}Service.saveOrUpdate(<#if controllerMappingHyphenStyle??>${controllerMappingHyphen}<#else>${table.entityPath}</#if>);
        if(bool){
            return ResultModel.success(<#if controllerMappingHyphenStyle??>${controllerMappingHyphen}<#else>${table.entityPath}</#if>);
        }else{
            return ResultModel.error(bool);
        }
    }

    @ApiOperation(value = "单个或批量删除", notes = "单个或批量删除")
    @PostMapping("/deleteBatch")
    public ResultModel<Boolean> deleteBatch(@RequestBody @ApiParam("多个ID")List<Long> ids) {
        boolean del = i${table.entityName}Service.removeByIds(ids);
        return ResultModel.success(del);
    }

    @ApiOperation(value = "根据id获取${table.comment!}", notes = "根据id获取${table.comment!}")
    @PostMapping("/get")
        public ResultModel<${table.entityName}> get(@RequestBody BaseModel model) {
        return ResultModel.success(i${table.entityName}Service.getById(model.getId()));
    }

}
</#if>