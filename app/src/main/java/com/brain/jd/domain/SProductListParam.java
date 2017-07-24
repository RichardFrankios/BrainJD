package com.brain.jd.domain;

import java.util.HashMap;

/**
 * 搜索商品参数
 * @author : Brian
 * @date : 2017/7/24
 */

public class SProductListParam {

    public SProductListParam(double categoryId) {
        this.id = categoryId;
    }

    /**
     * 分类id
     */
    private double id;
    /**
     * 排序类型（1-综合 2-新品 3-评价）
     */
    private int filterType;

    /**
     * 排序条件（0-默认 1-销量 2-价格高到低 3-价格低到高）
     */
    private int sortType;

    /**
     * 选择类型（0-代表无选择 1代表京东配送 2-代表货到付款 4-代表仅看有货 3代表条件1+2 5代表条件1+4 6代表条件2+4）
     */
    private int deliverChoose;



    private int minPrice = -1;
    private int maxPrice = -1;
    private int brandId = -1;


    @Override
    public String toString() {
        return "SProductListParam{" +
                "id=" + id +
                ", filterType='" + filterType + '\'' +
                ", sortType='" + sortType + '\'' +
                ", deliverChoose='" + deliverChoose + '\'' +
                ", minPrice=" + minPrice +
                ", maxPrice=" + maxPrice +
                ", brandId=" + brandId +
                '}';
    }

    public double getId() {
        return id;
    }

    public void setId(double id) {
        this.id = id;
    }

    public int getFilterType() {
        return filterType;
    }

    public void setFilterType(int filterType) {
        this.filterType = filterType;
    }

    public int getSortType() {
        return sortType;
    }

    public void setSortType(int sortType) {
        this.sortType = sortType;
    }

    public int getDeliverChoose() {
        return deliverChoose;
    }

    public void setDeliverChoose(int deliverChoose) {
        this.deliverChoose = deliverChoose;
    }

    public int getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(int minPrice) {
        this.minPrice = minPrice;
    }

    public int getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(int maxPrice) {
        this.maxPrice = maxPrice;
    }

    public int getBrandId() {
        return brandId;
    }

    public void setBrandId(int brandId) {
        this.brandId = brandId;
    }

    public HashMap<String, String> getHashMapValue() {
        HashMap<String, String> param = new HashMap<>();
        param.put("categoryId", (int)id + "");
        param.put("filterType", filterType + "");
        if (sortType != 0)
            param.put("sortType", sortType + "");

        param.put("deliverChoose", deliverChoose + "");

        if (maxPrice != -1 && (minPrice != -1) && (maxPrice > minPrice)) {
            param.put("minPrice", minPrice + "");
            param.put("maxPrice", maxPrice + "");
        }

        if (brandId != -1) {
            param.put("brandId", brandId + "");
        }

        return param;
    }
}
