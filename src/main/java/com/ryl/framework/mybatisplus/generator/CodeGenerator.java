package com.ryl.framework.mybatisplus.generator;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableFill;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import com.google.common.base.CaseFormat;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: ryl
 * @description:
 * @date: 2020-07-17 17:42:57
 */
@Component
public class CodeGenerator {

    @Value("${spring.datasource.url:}")
    private String url;
    @Value("${spring.datasource.driver-class-name:}")
    private String driver;
    @Value("${spring.datasource.username:}")
    private String username;
    @Value("${spring.datasource.password:}")
    private String password;
    @Value("${spring.generator.parent:}")
    private String parent;
    @Value("${spring.generator.module:}")
    private String module;


    public void run(String author, String[] tables, String[] excludeTables, String[] ignorePrefixes) {
        // 代码生成器
        AutoGenerator mpg = new AutoGenerator();

        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        final String projectPath = System.getProperty("user.dir");
        gc.setOutputDir(projectPath + "/src/main/java");
        gc.setFileOverride(true);
        gc.setAuthor(author);
        gc.setOpen(false);
        gc.setSwagger2(true);
        mpg.setGlobalConfig(gc);

        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl(url);
        // dsc.setSchemaName("public");
        dsc.setDriverName(driver);
        dsc.setUsername(username);
        dsc.setPassword(password);
        mpg.setDataSource(dsc);

        // 包配置
        PackageConfig pc = new PackageConfig();
        pc.setModuleName(module);
        pc.setParent(parent);
        pc.setEntity("model.entity");
        mpg.setPackageInfo(pc);

        // 自定义配置
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                Map<String, Object> map = new HashMap();
                map.put("ModelVO", pc.getParent() + ".model.vo");
                map.put("ModelDTO", pc.getParent() + ".model.dto");
                map.put("moduleName", CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, pc.getModuleName()));
                this.setMap(map);
            }
        };

        String xml = "/templates/mapper.xml.ftl";
        List<FileOutConfig> focList = new ArrayList();
        focList.add(new FileOutConfig(xml) {
            public String outputFile(TableInfo tableInfo) {
                return projectPath + "/src/main/resources/mapper/" + pc.getModuleName().replace(".", "/") + "/" + tableInfo.getEntityName() + "Mapper.xml";
            }
        });
        String entityVO = "/templates/entityVO.java.ftl";
        focList.add(new FileOutConfig(entityVO) {
            public String outputFile(TableInfo tableInfo) {
                return projectPath + "/src/main/java//" + pc.getParent().replace(".", "/") + "/model/vo/" + tableInfo.getEntityName() + "VO.java";
            }
        });
        String entityDTO = "/templates/entityDTO.java.ftl";
        focList.add(new FileOutConfig(entityDTO) {
            public String outputFile(TableInfo tableInfo) {
                return projectPath + "/src/main/java//" + pc.getParent().replace(".", "/") + "/model/dto/" + tableInfo.getEntityName() + "DTO.java";
            }
        });

//        cfg.setFileCreate(new IFileCreate() {
//            @Override
//            public boolean isCreate(ConfigBuilder configBuilder, FileType fileType, String filePath) {
//                // 判断自定义文件夹是否需要创建
//                checkDir("调用默认方法创建的目录，自定义目录用");
//                if (fileType == FileType.MAPPER) {
//                    // 已经生成 mapper 文件判断存在，不想重新生成返回 false
//                    return !new File(filePath).exists();
//                }
//                // 允许生成模板文件
//                return true;
//            }
//        });

        cfg.setFileOutConfigList(focList);
        mpg.setCfg(cfg);

        // 配置模板
        TemplateConfig templateConfig = new TemplateConfig();
        // 配置自定义输出模板
        //指定自定义模板路径，注意不要带上.ftl/.vm, 会根据使用的模板引擎自动识别
        // templateConfig.setEntity("templates/entity2.java");
        // templateConfig.setService();
        // templateConfig.setController();
        templateConfig.setXml(null);
        mpg.setTemplate(templateConfig);

        List<TableFill> tableFillList = this.getTableFills();
        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        strategy.setEntityLombokModel(true);
        strategy.setRestControllerStyle(true);
        strategy.setTablePrefix(ignorePrefixes);//表前缀
        // 公共父类Entity
        strategy.setSuperEntityClass("com.ryl.framework.mybatisplus.model.BaseEntity");
        // 公共父类Controller
        //strategy.setSuperControllerClass("com.ryl.framework.mybatisplus.controller.BaseController");
        // 写于父类中的公共字段
        strategy.setSuperEntityColumns(new String[]{"create_user_id", "modify_user_id", "create_time", "modify_time", "state_deleted"});
        strategy.setTableFillList(tableFillList);
        if (StringUtils.checkValNotNull(tables)) {
            strategy.setInclude(tables);
        } else if (excludeTables != null && excludeTables.length > 0 && ObjectUtils.isNotEmpty(excludeTables[0])) {
            strategy.setExclude(excludeTables);
        }
        strategy.setControllerMappingHyphenStyle(false);
        mpg.setStrategy(strategy);
        mpg.setTemplateEngine(new FreemarkerTemplateEngine());
        mpg.execute();

    }

    protected List<TableFill> getTableFills() {
        List<TableFill> tableFillList = new ArrayList();
        tableFillList.add(new TableFill("create_time", FieldFill.INSERT));
        tableFillList.add(new TableFill("modify_time", FieldFill.INSERT_UPDATE));
        tableFillList.add(new TableFill("create_user_id", FieldFill.INSERT));
        tableFillList.add(new TableFill("modify_user_id", FieldFill.INSERT_UPDATE));
        return tableFillList;
    }
}
