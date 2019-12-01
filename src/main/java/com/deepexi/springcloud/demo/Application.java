package com.deepexi.springcloud.demo;

import com.deepexi.springcloud.demo.service.LoopNoticeServie;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * Created by donh on 2018/11/5.
 * EnableDiscoveryClient 用于启动服务发现功能
 * EnableFeignClients 用于启动Fegin功能
 */
@EnableDiscoveryClient
@EnableFeignClients
@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(Application.class, args);

        LoopNoticeServie loopNoticeServie = context.getBean(LoopNoticeServie.class);

        System.out.println(loopNoticeServie);

        new Thread(() -> {
            loopNoticeServie.loopNotice();
        }).start();
    }
}