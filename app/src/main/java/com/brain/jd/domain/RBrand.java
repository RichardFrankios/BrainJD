package com.brain.jd.domain;

/**
 * @author : Brian
 * @date : 2017/7/24
 */

public class RBrand {
    private double id;
    private String name;

    @Override
    public String toString() {
        return "RBrand{" +
                "id=" + id +
                ", name='" + name + '\'' +
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
}
