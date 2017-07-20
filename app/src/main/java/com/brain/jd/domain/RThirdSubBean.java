package com.brain.jd.domain;

/**
 * 三级分类
 * @author : Brian
 * @date : 2017/7/20
 */

public class RThirdSubBean {
//     "id": 8,
//             "bannerUrl": "/img/zzs.jpg",
//             "name": "针织衫"

    private double id;
    private String bannerUrl;
    private String name;

    @Override
    public String toString() {
        return "RThirdSubBean{" +
                "id=" + id +
                ", bannerUrl='" + bannerUrl + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

    public double getId() {
        return id;
    }

    public void setId(double id) {
        this.id = id;
    }

    public String getBannerUrl() {
        return bannerUrl;
    }

    public void setBannerUrl(String bannerUrl) {
        this.bannerUrl = bannerUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
