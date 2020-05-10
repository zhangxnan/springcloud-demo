package com.deepexi.springcloud.demo.service;


import cn.hutool.core.lang.Pair;

import java.util.Map;

/**
 * <p> 文件下载 </p>
 *
 * @author zhangnan
 * @date 2020-05-10 17:30
 */
public interface FileDowloadService {

    /**
     * 下载文件
     */
    Pair<String, String> dowloadNowFile();

    /**
     * 读取csv文件
     *
     * @param url url
     * @return
     */
    Map<String, String> readCsv(String url);


}
