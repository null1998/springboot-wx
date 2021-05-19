package com.laoh;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScan;


/**
 * @author yanduohuang
 */
@SpringBootApplication
@ComponentScan("com.hyd.common")
@ComponentScan("com.laoh")
@MapperScan(basePackages = {"com.laoh.demo.dao"})
@ServletComponentScan(basePackages = "com.laoh.demo.web.servlet")
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
