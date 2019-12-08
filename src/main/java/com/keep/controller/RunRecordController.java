package com.keep.controller;

import com.deepexi.springcloud.demo.utils.Result;
import com.keep.domain.CluRunRecord;
import com.keep.domain.ClubUser;
import com.keep.service.ClubRunRecordService;
import com.keep.service.CludUserService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <p> 运动控制类 </p>
 *
 * @author zhangnan
 * @date 2019-12-08 15:22
 */
@RestController
@RequestMapping("/run")
public class RunRecordController {

    @Resource
    private CludUserService userService;

    @Resource
    private ClubRunRecordService runRecordService;

    private static final Logger log = LoggerFactory.getLogger(RunRecordController.class);


    @GetMapping("/record")
    public Result record(@RequestParam("sys.ding.senderId") String dingId,
                         @RequestParam("sys.ding.senderNick") String nickName,
                         @RequestParam("sys.ding.conversationTitle") String title,
                         @RequestParam("sys.ding.conversationId") String conversationId,
                         @RequestParam("duration")Double duration,
                         @RequestParam("distance")Double distance) {

        log.error("dingId:{}, nicName:{}, title:{}, conversationId:{}, duration:{}, distance{},"
        , dingId, nickName, title, conversationId, duration, distance);

        if (StringUtils.isEmpty(dingId) || StringUtils.isEmpty(nickName)){
            return Result.getFailResult("缺少参数");
        }

        ClubUser clubUser = new ClubUser();
        clubUser.setDingId(dingId);
        clubUser.setNickName(nickName);
        clubUser.setTelnetId(conversationId);
        clubUser.setTitle(title);

        Long userId = userService.createUser(clubUser);

        CluRunRecord record = new CluRunRecord();
        record.setDuration(duration);
        record.setDistance(distance);
        record.setUserId(userId);

        runRecordService.saveCluRunRecord(record);

        return Result.getSuccessResult();
    }


}
