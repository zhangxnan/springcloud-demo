package com.keep.utils;

import com.dingtalk.api.DefaultDingTalkClient;
import com.dingtalk.api.DingTalkClient;
import com.dingtalk.api.request.OapiRobotSendRequest;
import com.taobao.api.ApiException;
import org.apache.commons.lang3.StringUtils;

/**
 * <p> 发送钉钉消息 </p>
 *
 * @author zhangnan
 * @date 2019-11-02 20:59
 */
public class RunDingDingMessage {
    private static final String DING_DING_URL = "https://oapi.dingtalk.com/robot/send?access_token=fe8064c630f522dd73691bb118acf4b82657809e61bf4ff943999c600d824b17";

    public void sendMessage(String message) throws ApiException {
            send(message, DING_DING_URL, null);
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
