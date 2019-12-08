package com.keep.param;

import org.springframework.web.bind.annotation.RequestParam;

/**
 * <p> 钉钉基础信息 </p>
 *
 * @author zhangnan
 * @date 2019-12-08 15:27
 */
public class BaseDingDingParam {

    private String dingId;

    private String nickName;

    private String telnetId;

    private String title;

    public String getDingId() {
        return dingId;
    }

    public void setDingId(String dingId) {
        this.dingId = dingId;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getTelnetId() {
        return telnetId;
    }

    public void setTelnetId(String telnetId) {
        this.telnetId = telnetId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "BaseDingDingParam{" +
                "dingId='" + dingId + '\'' +
                ", nickName='" + nickName + '\'' +
                ", telnetId='" + telnetId + '\'' +
                ", title='" + title + '\'' +
                '}';
    }
}
