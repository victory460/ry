package com.anke.yingxiang.domain;

/**
 * Created by qingxiangzhang on 2017/11/30.
 */
public class MyTask {

    private String id;
    private String productId;
    private String productName;
    private String title;
    private String type;
    private boolean isReopen;
    private String operator;
    private String date;
    private String state;

    public MyTask(String  id, String productId, String productName, String title, String type, boolean isReopen, String operator, String date, String state) {
        this.id = id;
        this.productId = productId;
        this.productName = productName;
        this.title = title;
        this.type = type;
        this.isReopen = isReopen;
        this.operator = operator;
        this.date = date;
        this.state = state;
    }

    public String  getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isReopen() {
        return isReopen;
    }

    public void setReopen(boolean reopen) {
        isReopen = reopen;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
