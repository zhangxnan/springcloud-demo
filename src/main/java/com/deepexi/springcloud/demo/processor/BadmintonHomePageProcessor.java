package com.deepexi.springcloud.demo.processor;

import com.deepexi.springcloud.demo.constants.ClubUrls;
import com.deepexi.springcloud.demo.controller.BadmintonController;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.SpiderListener;
import us.codecraft.webmagic.processor.PageProcessor;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * <p> TODO </p>
 *
 * @author zhangnan
 * @date 2019-11-02 19:28
 */
public class BadmintonHomePageProcessor implements PageProcessor {
    private Site site = Site.me().setRetryTimes(3).setSleepTime(1000);

    private static final Logger log = LoggerFactory.getLogger(BadmintonHomePageProcessor.class);


    private Integer number = 1;

    public BadmintonHomePageProcessor(Integer number) {
        this.number = number;
    }

    @Override
    public void process(Page page) {
        if (ClubUrls.HOME_PAGE.equalsIgnoreCase(page.getUrl().toString())){
            List<String> alls = page.getHtml().regex("/activity/info/id/.{24}").all();
            Set<String> detailUrls = new HashSet<>();

            for (String url : alls) {
                if (StringUtils.isNotEmpty(url)){
                    detailUrls.add(url);
                }
                if (detailUrls.size() >= number){
                    break;
                }
                detailUrls.add(url);
            }

            for (String detailUrl : detailUrls) {
                page.addTargetRequest(ClubUrls.HOME_PAGE + detailUrl);
            }

            return;
        }

        System.out.println(page.getHtml().toString());
        getInfoAndSendMessage(page);

    }

    private void getInfoAndSendMessage(Page page) {
        List<String> target = page.getHtml().regex("<div id=\"copydiv\" style=\"display:none;\">(.{0,1000})\\</div>").all();

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
            log.error("发送钉钉消息失败", e);
        }
    }

    @Override
    public Site getSite() {
        return site;
    }



    public static void getPageAndSendMessage(Integer number) {
        Spider spider = Spider.create(new BadmintonHomePageProcessor(number)).addUrl(ClubUrls.HOME_PAGE);

        List<SpiderListener> spiderListeners = new ArrayList<>();
        SpiderListener listener = new BadmintonSpiderListener();
        spiderListeners.add(listener);
        spider.setSpiderListeners(spiderListeners);

        spider.run();

        spider.stop();
    }

    public static void main(String[] args) {
        getPageAndSendMessage(1);
    }

}
