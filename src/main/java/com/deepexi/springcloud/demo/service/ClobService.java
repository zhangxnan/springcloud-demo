package com.deepexi.springcloud.demo.service;

/**
 * <p> TODO </p>
 *
 * @author zhangnan
 * @date 2019-11-09 13:02
 */
public interface ClobService {
    /**
     * 发送俱乐部介绍到钉钉
     * @param name 接受消息的用户
     */
    void sendClobDetailToDingDing(String name);

    /**
     * 发送积分
     * @param aName
     */
    void sendscore(String aName);

    /**
     * 发送全部积分
     * @param name
     */
    void sendAllScore(String name);
}
