package com.brain.jd.domain;

/**
 * @author : Brian
 * @date : 2017/6/22
 */

public class RLoginResult {
    /**
     * 用户id
     */
    public long id;
    /**
     * 用户名
     */
    public String userName;
    /**
     * 头像路径
     */
    public String userIcon;
    /**
     * 待付款数
     */
    public int waitPayCount;
    /**
     * 待收货数
     */
    public int waitReceiveCount;
    /**
     * 用户等级（1注册会员2铜牌会员3银牌会员4金牌会员5钻石会员）
     */
    public int userLevel;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserIcon() {
        return userIcon;
    }

    public void setUserIcon(String userIcon) {
        this.userIcon = userIcon;
    }

    public int getWaitPayCount() {
        return waitPayCount;
    }

    public void setWaitPayCount(int waitPayCount) {
        this.waitPayCount = waitPayCount;
    }

    public int getWaitReceiveCount() {
        return waitReceiveCount;
    }

    public void setWaitReceiveCount(int waitReceiveCount) {
        this.waitReceiveCount = waitReceiveCount;
    }

    public int getUserLevel() {
        return userLevel;
    }

    public void setUserLevel(int userLevel) {
        this.userLevel = userLevel;
    }

    @Override
    public String toString() {
        return "RLoginResult{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", userIcon='" + userIcon + '\'' +
                ", waitPayCount=" + waitPayCount +
                ", waitReceiveCount=" + waitReceiveCount +
                ", userLevel=" + userLevel +
                '}';
    }
}
