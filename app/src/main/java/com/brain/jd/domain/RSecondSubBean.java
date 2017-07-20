package com.brain.jd.domain;

/**
 * 二级分类, Bean
 * @author : Brian
 * @date : 2017/7/20
 */

public class RSecondSubBean {
//     "id": 14,
//             "name": "上装",
//             "thirdCategory":

    private double id;
    private String name;
    private String thirdCategory;

    @Override
    public String toString() {
        return "RSecondSubBean{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", thirdCategory='" + thirdCategory + '\'' +
                '}';
    }

    public double getId() {
        return id;
    }

    public void setId(double id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getThirdCategory() {
        return thirdCategory;
    }

    public void setThirdCategory(String thirdCategory) {
        this.thirdCategory = thirdCategory;
    }
}
