package com.deepexi.springcloud.demo.service.impl;

import com.deepexi.springcloud.demo.constants.ClubUrls;
import com.deepexi.springcloud.demo.controller.BadmintonController;
import com.deepexi.springcloud.demo.processor.DingDingMessage;
import com.deepexi.springcloud.demo.service.ClobService;
import com.taobao.api.ApiException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * <p> 俱乐部服务 </p>
 *
 * @author zhangnan
 * @date 2019-11-09 13:02
 */
@Service
public class ClobServiceImpl implements ClobService {

    private static final Logger log = LoggerFactory.getLogger(BadmintonController.class);


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

}
