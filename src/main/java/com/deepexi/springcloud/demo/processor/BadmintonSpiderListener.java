package com.deepexi.springcloud.demo.processor;

import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.SpiderListener;

/**
 * <p> TODO </p>
 *
 * @author zhangnan
 * @date 2019-11-02 19:40
 */
public class BadmintonSpiderListener implements SpiderListener {


    @Override
    public void onSuccess(Request request) {
        System.out.println(request);

    }

    @Override
    public void onError(Request request) {

    }
}
