package com.keep.service.impl;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.date.Week;
import com.deepexi.springcloud.demo.service.LoopNoticeServie;
import com.keep.domain.CluRunRecord;
import com.keep.service.ClubRunRecordService;
import com.keep.utils.RunDingDingMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * <p> 循环通知服务 </p>
 *
 * @author zhangnan
 * @date 2019-12-08 16:13
 */
@Service
public class RunLoopNoticeServieImpl implements LoopNoticeServie {

    private static final Logger log = LoggerFactory.getLogger(RunLoopNoticeServieImpl.class);

    @Resource
    private ClubRunRecordService runRecordService;

    @Override
    public void loopNotice() {
        while (true){
            try {
                TimeUnit.MINUTES.sleep(1);
            }catch (Exception e){

            }
            Date now = new Date();
            Week dayOfWeek = DateUtil.dayOfWeekEnum(now);


            if (!dayOfWeek.equals(Week.THURSDAY)){
                continue;
            }

            if (DateUtil.hour(now, true) != 10){
                continue;
            }

            if (DateUtil.minute(now) != 1){
                continue;
            }

            DateTime startTime = DateUtil.offsetDay(now, -7);

            List<CluRunRecord> records = runRecordService.getAllRunList(DateUtil.format(startTime, "yyyy-MM-dd"));
            if (records == null || records.isEmpty()){
                continue;
            }

            Map<String, List<CluRunRecord>> collect = records.stream().collect(Collectors.groupingBy(CluRunRecord::getNickName));

            StringBuilder builder = new StringBuilder();
            builder.append("大家上周(")
                    .append(DateUtil.format(startTime, "MM-dd"))
                    .append(" - ")
                    .append(DateUtil.format(now, "MM-dd"))
                    .append(")运动情况如下：\n");

            for (String name : collect.keySet()){
                builder.append("姓名: ")
                        .append(name)
                        .append("\n")
                        .append("总运动时长: ");
                List<CluRunRecord> runRecords = collect.get(name);
                Optional<Double> duration = runRecords.stream().map(CluRunRecord::getDuration).reduce(Double::sum);
                if (duration.isPresent()){
                    builder.append(duration.get()).append(" 分钟");
                }else {
                    builder.append("0 分钟");
                }
                builder.append("\n")
                    .append("总运动距离：");
                Optional<Double> distance = runRecords.stream().map(CluRunRecord::getDistance).reduce(Double::sum);
                if (distance.isPresent()){
                    builder.append(distance.get()).append(" 公里");
                }else {
                    builder.append("0 公里");
                }
                builder.append("\n --------------------------------- \n");
            }

            RunDingDingMessage message = new RunDingDingMessage();
            try {
                message.sendMessage(builder.toString());
            }catch (Exception e){
                log.error("发送钉钉消息失败", e);
            }
        }

    }
}
