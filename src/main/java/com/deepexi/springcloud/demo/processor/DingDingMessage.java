package com.deepexi.springcloud.demo.processor;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.date.Week;
import com.dingtalk.api.DefaultDingTalkClient;
import com.dingtalk.api.DingTalkClient;
import com.dingtalk.api.request.OapiRobotSendRequest;
import com.dingtalk.api.response.OapiRobotSendResponse;
import com.taobao.api.ApiException;
import org.apache.commons.lang3.StringUtils;

import java.util.Date;

/**
 * <p> 发送钉钉消息 </p>
 *
 * @author zhangnan
 * @date 2019-11-02 20:59
 */
public class DingDingMessage {
    private static final String DING_DING_URL = "https://oapi.dingtalk.com/robot/send?access_token=2ae8eb2c1b2685865d42a6e8949c5d1b9bbaa3d66aa8ffb9a25477dcd070625a";

    private static final String YU_DINGDING_WEB_HOOK = "https://oapi.dingtalk.com/robot/send?access_token=4e349bf573fa125a5e60cc96549c345e9b215e43c30efa4d3e9a2866a5a28345";

    /**
     * 千与千寻 钉钉群
     */
    private static final String QIAN_XUN_DING_URL  = "https://oapi.dingtalk.com/robot/send?access_token=4e349bf573fa125a5e60cc96549c345e9b215e43c30efa4d3e9a2866a5a28345";

    public void sendMessageByDefault(String message) throws ApiException {
        if (filter(message)){
            send(message, DING_DING_URL, null);
        }
    }

    public void sendMessage(String message) throws ApiException {
        if (filter(message)){
            send(message, QIAN_XUN_DING_URL, null);
        }
    }


    private boolean isSendTime(){

        Date now = new Date();
        Week dayOfWeek = DateUtil.dayOfWeekEnum(now);

        if (dayOfWeek.equals(Week.TUESDAY) || dayOfWeek.equals(Week.FRIDAY)){
            if (DateUtil.hour(now, true) == 9
                && DateUtil.minute(now) == 30){
                return true;
            }
        }

        return false;
    }





    private boolean filter(String message){
        if (message.contains("被取消")){
            return false;
        }else if (message.contains("已结束")){
            return false;
        }
        return true;
    }


    /**
     * 发送消息
     * @param message
     * @param url
     * @param at
     * @throws ApiException
     */
    private void send(String message, String url, String at) throws ApiException {
        DingTalkClient client = new DefaultDingTalkClient(url);
        OapiRobotSendRequest request = new OapiRobotSendRequest();
        request.setMsgtype("text");
        if (StringUtils.isNotEmpty(at)){
            request.setAt(at);
        }
        OapiRobotSendRequest.Text text = new OapiRobotSendRequest.Text();
        text.setContent(message);
        request.setText(text);
        client.execute(request);
    }

}
