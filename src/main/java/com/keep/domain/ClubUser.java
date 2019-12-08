package com.keep.domain;

import java.util.Date;

/**
 * <p> TODO </p>
 *
 * @author zhangnan
 * @date 2019-12-08 15:31
 */
public class ClubUser {

    private Long userId;

    private String dingId;

    private String nickName;

    private String telnetId;

    private String title;

    private Date createTime;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
