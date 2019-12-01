package com.deepexi.springcloud.demo.service.impl;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.date.Week;
import com.deepexi.springcloud.demo.controller.BadmintonController;
import com.deepexi.springcloud.demo.processor.BadmintonHomePageProcessor;
import com.deepexi.springcloud.demo.service.LoopNoticeServie;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * <p> TODO </p>
 *
 * @author zhangnan
 * @date 2019-12-01 13:26
 */
@Service
public class LoopNoticeServieImpl implements LoopNoticeServie {

    private static final Logger log = LoggerFactory.getLogger(BadmintonController.class);


    @Override
    public void loopNotice() {
        Week dayOfWeek = DateUtil.dayOfWeekEnum(new Date());

        while (true){
            try {
                TimeUnit.MINUTES.sleep(1);
            }catch (Exception e){

            }
            Date now = new Date();


            if (DateUtil.hour(now, true) != 9){
                continue;
            }

            if (DateUtil.minute(now) != 30){
                continue;
            }

            if (dayOfWeek.equals(Week.SUNDAY)){
                continue;
            }

            BadmintonHomePageProcessor.getPageAndSendMessage(2);
            log.info("执行完毕 .... now: " + LocalDateTime.now());

        }


    }

    public static void main(String[] args) {
        System.out.println(DateUtil.minute(new Date()));
    }
}
