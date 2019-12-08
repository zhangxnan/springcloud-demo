package com.keep.controller;

import com.deepexi.springcloud.demo.utils.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p> 运动控制类 </p>
 *
 * @author zhangnan
 * @date 2019-12-08 15:22
 */
@RestController
public class RunRecordController {


    @GetMapping("/new")
    public Result newMan(@RequestParam String key){


        return Result.getSuccessResult();
    }
}
