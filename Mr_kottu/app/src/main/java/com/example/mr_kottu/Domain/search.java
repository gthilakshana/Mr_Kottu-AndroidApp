package com.example.mr_kottu.Domain;

public class search {
    private String productName;
    private String productDesc;
    private String productPrice;
    private String imageURL;

    public search(String productName, String productDesc, String productPrice, String imageURL) {
        this.productName = productName;
        this.productDesc = productDesc;
        this.productPrice = productPrice;
        this.imageURL = imageURL;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductDesc() {
        return productDesc;
    }

    public void setProductDesc(String productDesc) {
        this.productDesc = productDesc;
    }

    public String getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(String productPrice) {
        this.productPrice = productPrice;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }
}
