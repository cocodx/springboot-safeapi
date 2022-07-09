package com.lg.safeapi;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author 正能量导师
 * @date 2022/6/14-16:59
 * @Description
 */
@EnableScheduling//开启定时任务
@PropertySource("classpath:datasource.properties")
@SpringBootApplication
@Slf4j
public class SafeapiStarter {

    public static void main(String[] args) {
        SpringApplication.run(SafeapiStarter.class,args);
        log.info("主函数启动：{}","success");
    }
}
