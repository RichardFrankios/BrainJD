package com.brain.jd.domain;

/**
 * @author : Brian
 * @date : 2017/7/19
 */

public class RTopCategoryBean {
//    "id": 分类id,
//            "bannerUrl": "分类图片路径",
//            "name": "分类名称"

    private double id;
    private String bannerUrl;
    private String name;

    @Override
    public String toString() {
        return "RTopCategoryBean{" +
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
