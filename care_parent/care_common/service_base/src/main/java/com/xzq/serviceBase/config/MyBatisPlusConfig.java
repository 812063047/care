package com.xzq.serviceBase.config;

import com.baomidou.mybatisplus.core.injector.ISqlInjector;
import com.baomidou.mybatisplus.extension.injector.LogicSqlInjector;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@MapperScan("com.xzq.*.mapper")
public class MyBatisPlusConfig {


    /**
     * @Description: SQL 执行性能分析插件
     *
     * 开发环境使用，线上不推荐。 maxTime 指的是 sql 最大执行时长
     * dev:开发环境
     * test:测试环境
     * prod:生成环境
     * @Author xuzhiqiang
     * @Date 2021/1/19 16:54
     */
   /* @Bean
    @Profile({"dev","test"})
    public PerformanceInterceptor performanceInterceptor(){
        PerformanceInterceptor performanceInterceptor = new PerformanceInterceptor();
        performanceInterceptor.setMaxTime(2000);
        performanceInterceptor.setFormat(true);
        return  performanceInterceptor;
    }*/
    /**
     * @Description: 配置逻辑删除
     * @Author xuzhiqiang
     * @Date 2021/1/20 11:20
     */
    @Bean
    public ISqlInjector iSqlInjector(){

        return  new LogicSqlInjector();
    }

    @Bean
    public PaginationInterceptor paginationInterceptor(){
        return new PaginationInterceptor();
    }

}
