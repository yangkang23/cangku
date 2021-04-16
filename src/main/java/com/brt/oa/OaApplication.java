package com.brt.oa;

import io.github.yedaxia.apidocs.Docs;
import io.github.yedaxia.apidocs.DocsConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class OaApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(OaApplication.class, args);


        DocsConfig config = new DocsConfig();
        config.setProjectPath("E:\\User\\ASUS\\Desktop\\IDEAworkspace\\oa\\src\\main\\java"); // 项目根目录
        config.setProjectName("oa"); // 项目名称
        config.setApiVersion("api");       // 声明该API的版本
        config.setDocsPath("E:\\User\\ASUS\\Desktop"); // 生成API 文档所在目录
        config.setAutoGenerate(Boolean.TRUE);  // 配置自动生成
        Docs.buildHtmlDocs(config); // 执行生成文档
    }



}
