package com.example.hyvu.banhang.model;

import java.io.Serializable;

public class Categories implements Serializable {
    int id;
    String img;
    String category;

    public Categories(int id, String category, String img) {
        this.id = id;
        this.img = img;
        this.category = category;
    }

    public Categories(String img, String category) {
        this.img = img;
        this.category = category;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
