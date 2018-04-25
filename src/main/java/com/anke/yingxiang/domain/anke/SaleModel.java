package com.anke.yingxiang.domain.anke;

public class SaleModel {

    private ClientModel Client;
    private DeviceModel Product;
    private String Id;
    private String ClientId;
    private String ProductId;

    public ClientModel getClient() {
        return Client;
    }

    public void setClient(ClientModel client) {
        Client = client;
    }

    public DeviceModel getProduct() {
        return Product;
    }

    public void setProduct(DeviceModel product) {
        Product = product;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getClientId() {
        return ClientId;
    }

    public void setClientId(String clientId) {
        ClientId = clientId;
    }

    public String getProductId() {
        return ProductId;
    }

    public void setProductId(String productId) {
        ProductId = productId;
    }
}