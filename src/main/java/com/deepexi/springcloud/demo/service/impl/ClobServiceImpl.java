package com.deepexi.springcloud.demo.service.impl;

import cn.hutool.core.lang.Pair;
import cn.hutool.core.map.MapUtil;
import com.deepexi.springcloud.demo.constants.ClubUrls;
import com.deepexi.springcloud.demo.controller.BadmintonController;
import com.deepexi.springcloud.demo.processor.DingDingMessage;
import com.deepexi.springcloud.demo.service.ClobService;
import com.deepexi.springcloud.demo.service.FileDowloadService;
import com.taobao.api.ApiException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

/**
 * <p> 俱乐部服务 </p>
 *
 * @author zhangnan
 * @date 2019-11-09 13:02
 */
@Service
public class ClobServiceImpl implements ClobService {

    private static final Logger log = LoggerFactory.getLogger(BadmintonController.class);


    @Resource
    private FileDowloadService dowloadService;

    @Override
    public void sendClobDetailToDingDing(String name) {

        String detail = name + "你好，欢迎加入阿里巴巴-中羽联俱乐部。" +
                "我们将定期组织羽毛球活动和羽毛球教学[分初级、中级、高级]。费用AA" +
                "，让我们一起愉快的打羽毛球吧^-^,首先打开这个链接，注册一下下哦"
                + ClubUrls.HOME_PAGE;
        try {
            new DingDingMessage().sendMessage(detail);
        } catch (ApiException e) {
            log.error("发送钉钉消息异常",e);
        }
    }

    @Override
    public void sendscore(String name) {
        Pair<String, String> pair = dowloadService.dowloadNowFile();

        Map<String, String> name2ScoreNew = dowloadService.readCsv(pair.getKey());

        String detail;
        if (!name2ScoreNew.containsKey(name)){
            detail = name + "你好, 暂时获取不到你的俱乐部积分，" +
                    "可能是你的钉钉名称和注册的中羽联不一致! 点击下面链接去修改昵称吧"
                    + ClubUrls.HOME_PAGE;
            return;
        }

        detail = name + "你好, 你目前的积分是: " + name2ScoreNew.get(name);

        try {
            new DingDingMessage().sendMessage(detail);
        } catch (ApiException e) {
            log.error("发送钉钉消息异常",e);
        }
    }

    @Override
    public void sendAllScore(String name) {
        Pair<String, String> pair = dowloadService.dowloadNowFile();
        Map<String, String> name2ScoreNew = dowloadService.readCsv(pair.getKey());

        if (MapUtil.isEmpty(name2ScoreNew)){
            return;
        }

        StringBuilder builder = new StringBuilder();
        for (String key : name2ScoreNew.keySet()) {
            builder.append("[" + key + " : " + name2ScoreNew.get(key) + "]  " + "\n");
        }

        try {
            new DingDingMessage().sendMessage(builder.toString());
        } catch (ApiException e) {
            log.error("发送钉钉消息异常",e);
        }

    }
}
