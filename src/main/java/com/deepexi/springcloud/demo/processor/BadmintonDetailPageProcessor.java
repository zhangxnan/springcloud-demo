package com.deepexi.springcloud.demo.processor;

import com.deepexi.springcloud.demo.constants.ClubUrls;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.SpiderListener;
import us.codecraft.webmagic.processor.PageProcessor;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p> TODO </p>
 *
 * @author zhangnan
 * @date 2019-11-02 19:28
 */
public class BadmintonDetailPageProcessor implements PageProcessor {
    private Site site = Site.me().setRetryTimes(3).setSleepTime(1000);


    private static final String DETAIL_URL = "http://anym.zhongyulian.com/activity/info/id/";


    @Override
    public void process(Page page) {
        System.out.println(page.getJson());

        List<String> target = page.getHtml().regex("<div id=\"copydiv\" style=\"display:none;\">(.{0,1000})\\</div>").all();

        System.out.println(target);

        target = target.stream()
                .map(x -> x.replaceAll(" ", "\n"))
                .map(x -> x.replaceAll("\n女"," 女")
                            .replaceAll("\n青", " 青")
                            .trim())
                .collect(Collectors.toList());

        DingDingMessage message = new DingDingMessage();
        try {
            message.sendMessage(target.get(0));
        }catch (Exception e){

        }
    }

    @Override
    public Site getSite() {
        return site;
    }


    public static void getPageAndSendMessage(String id){

        Spider spider = Spider.create(new BadmintonDetailPageProcessor()).addUrl(DETAIL_URL + id);

        List<SpiderListener> spiderListeners = new ArrayList<>();
        SpiderListener listener = new BadmintonSpiderListener();
        spiderListeners.add(listener);
        spider.setSpiderListeners(spiderListeners);

        spider.run();

        spider.stop();
    }


    public static void main(String[] args) {
        Spider spider = Spider.create(new BadmintonDetailPageProcessor()).addUrl(ClubUrls.HOME_PAGE);

        List<SpiderListener> spiderListeners = new ArrayList<>();
        SpiderListener listener = new BadmintonSpiderListener();
        spiderListeners.add(listener);
        spider.setSpiderListeners(spiderListeners);

        spider.run();

        spider.stop();
    }



}
