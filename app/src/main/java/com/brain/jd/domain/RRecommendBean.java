package com.brain.jd.domain;

/**
 * @author : Brian
 * @date : 2017/7/19
 */

public class RRecommendBean {
//     "price": 商品价格,
//            "name": "商品名称",
//            "iconUrl": "商品图片",
//            "productId": 商品id
    private double price;
    private String name;
    private String iconUrl;
    private int productId;

    @Override
    public String toString() {
        return "RRecommendBean{" +
                "price=" + price +
                ", name='" + name + '\'' +
                ", iconUrl='" + iconUrl + '\'' +
                ", productId=" + productId +
                '}';
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }
}
