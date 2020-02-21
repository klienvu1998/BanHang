package com.example.hyvu.banhang.model;

import java.io.Serializable;

public class Product implements Serializable {
    int id;
    String name;
    int price;
    String img;
    String desciption;
    int id_loaisp;

    public Product(int id, String name, int price, String img, String desciption, int id_loaisp) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.img = img;
        this.desciption = desciption;
        this.id_loaisp = id_loaisp;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getDesciption() {
        return desciption;
    }

    public void setDesciption(String desciption) {
        this.desciption = desciption;
    }

    public int getId_loaisp() {
        return id_loaisp;
    }

    public void setId_loaisp(int id_loaisp) {
        this.id_loaisp = id_loaisp;
    }
}
