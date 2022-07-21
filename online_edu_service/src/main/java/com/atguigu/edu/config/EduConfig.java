package com.atguigu.edu.config;

import com.baomidou.mybatisplus.core.injector.ISqlInjector;
import com.baomidou.mybatisplus.extension.injector.LogicSqlInjector;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author: 了飘尘
 * @date: 2022/3/31 10:22
 * @version: 1.0
 * @Description:
 */
@Configuration
public class EduConfig{
    /**
     * 分页插件
     * @return
     */
  @Bean
      public PaginationInterceptor paginationInterceptor() {
          return new PaginationInterceptor();
      }


    /**
     * 逻辑删除插件
     */
    @Bean
    public ISqlInjector sqlInjector() {
        return new LogicSqlInjector();
    }
}

