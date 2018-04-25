package com.anke.yingxiang.store;

/**
 * Created by qingxiangzhang on 2017/11/22.
 */
public class Product {

    private int _id;

    private String image;

    private String name;

    private Manufacturer manufacturer;

    private String price;

    private String description;

    public Product() {
    }

    public Product(int _id, String name, String image, Manufacturer manufacturer, String price) {
        this._id = _id;
        this.name = name;
        this.image = image;
        this.manufacturer = manufacturer;
        this.price = price;
    }

    public Product(int _id, String name, String image, Manufacturer manufacturer, String price, String description) {
        this._id = _id;
        this.name = name;
        this.image = image;
        this.manufacturer = manufacturer;
        this.price = price;
        this.description = description;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Manufacturer getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(Manufacturer manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
