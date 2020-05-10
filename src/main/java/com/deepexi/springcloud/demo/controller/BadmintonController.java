package com.deepexi.springcloud.demo.controller;

import com.deepexi.springcloud.demo.processor.BadmintonDetailPageProcessor;
import com.deepexi.springcloud.demo.processor.BadmintonHomePageProcessor;
import com.deepexi.springcloud.demo.service.ClobService;
import com.deepexi.springcloud.demo.utils.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * <p> TODO </p>
 *
 * @author zhangnan
 * @date 2019-11-03 16:12
 */
@RestController
public class BadmintonController {

    private static final String PW = "e75a6146-b98a-4529-bde1-cb19b6d0098b";

    private static final Logger log = LoggerFactory.getLogger(BadmintonController.class);

    @Resource
    private ClobService clobService;

    @GetMapping("/new")
    public Result newMan(@RequestParam String aName,
                         @RequestParam String aKey){
        if (checkKey(aKey)){
            return new Result(false, "秘钥错误", "-1");
        }
        clobService.sendClobDetailToDingDing(aName);
        return Result.getSuccessResult();
    }

    /**
     * 个人积分
     * @param aName
     * @param aKey
     * @return
     */
    @GetMapping("/score")
    public Result score(@RequestParam String aName,
                         @RequestParam String aKey){
        if (checkKey(aKey)){
            return new Result(false, "秘钥错误", "-1");
        }
        clobService.sendscore(aName);
        return Result.getSuccessResult();
    }

    /**
     * 所有人积分
     * @param aName
     * @param aKey
     * @return
     */
    @GetMapping("/allScore")
    public Result allScore(@RequestParam String aName,
                        @RequestParam String aKey){
        if (checkKey(aKey)){
            return new Result(false, "秘钥错误", "-1");
        }
        clobService.sendAllScore(aName);
        return Result.getSuccessResult();
    }


    @GetMapping("/loopCall")
    public Result loopCall(@RequestParam Integer aTimes,
                           @RequestParam Integer aTime,
                           @RequestParam Integer aNumber,
                           @RequestParam String aKey){
        if (checkKey(aKey)){
            return new Result(false, "秘钥错误", "-1");
        }

        run(() -> {
            for (int i = 1; i <= aTimes; i ++){
                BadmintonHomePageProcessor.getPageAndSendMessage(aNumber);
                try {

                    Random random = new Random();
                    int value = random.nextInt(300);

                    TimeUnit.SECONDS.sleep(aTime * 60 + value);

                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });

        return Result.getSuccessResult();
    }

    @GetMapping("/recently")
    public Result recently(@RequestParam Integer aNumber,
                           @RequestParam String aKey){
        if (checkKey(aKey)){
            return new Result(false, "秘钥错误", "-1");
        }

        run(() -> BadmintonHomePageProcessor.getPageAndSendMessage(aNumber));

        return Result.getSuccessResult();
    }


    private void run(Runnable runnable){
        new Thread(runnable).start();
    }


    @GetMapping("/send")
    public Result testFeign(@RequestParam String  aId,
                            @RequestParam Integer aTimes,
                            @RequestParam Integer aTime,
                            @RequestParam String aKey) {


        log.info(" id: "+aId+" times: "+aTimes+" time: "+aTime + "start ... ");

        if (checkKey(aKey)){
            return new Result(false, "秘钥错误", "-1");
        }


        if (null == aTimes || null == aTime){
            BadmintonDetailPageProcessor.getPageAndSendMessage(aId);
            return Result.getSuccessResult();
        }

        run(() -> {
            for (int i = 1; i <= aTimes; i ++){
                BadmintonDetailPageProcessor.getPageAndSendMessage(aId);
                try {

                    Random random = new Random();
                    int value = random.nextInt(300);

                    TimeUnit.SECONDS.sleep(aTime * 60 + value);

                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });


        return Result.getSuccessResult();
    }


    public static void main(String[] args) {
        System.out.println(UUID.randomUUID());
    }


    /**
     * @param key 密匙
     * @return 是否密匙正确
     */
    private boolean checkKey(String key){
        return !PW.equals(key);
    }


}
