package com.brain.jd.domain;

/**
 * @author : Brian
 * @date : 2017/7/24
 */

public class RProductListBean {

    private int total;
    private String rows;



    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public String getRows() {
        return rows;
    }

    public void setRows(String rows) {
        this.rows = rows;
    }

    @Override
    public String toString() {
        return "RProductListBean{" +
                "total=" + total +
                ", rows='" + rows + '\'' +
                '}';
    }

    public static class RProductInfoBean {
        private double id;
        private double price;
        private String name;
        private String iconUrl;
        private int commentCount;
        private int favcomRate;

        @Override
        public String toString() {
            return "RProductInfoBean{" +
                    "id=" + id +
                    ", price=" + price +
                    ", name='" + name + '\'' +
                    ", iconUrl='" + iconUrl + '\'' +
                    ", commentCount=" + commentCount +
                    ", favcomRate=" + favcomRate +
                    '}';
        }

        public double getId() {
            return id;
        }

        public void setId(double id) {
            this.id = id;
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

        public int getCommentCount() {
            return commentCount;
        }

        public void setCommentCount(int commentCount) {
            this.commentCount = commentCount;
        }

        public int getFavcomRate() {
            return favcomRate;
        }

        public void setFavcomRate(int favcomRate) {
            this.favcomRate = favcomRate;
        }
        //        "id": 商品id,
//                "price": 商品价格,
//                "name": "商品名称",
//                "iconUrl": "商品图片",
//                "commentCount": 评论数,
//                "favcomRate": 好评率
    }
}
