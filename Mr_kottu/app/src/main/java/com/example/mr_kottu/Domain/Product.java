package com.example.mr_kottu.Domain;

// Product.java
public class Product {
    private String imageURL ;

    private  String productDesc;

    private String productName;


    private String productPrice;
    private String productId;

    public Product(String imageURL, String productDesc, String productName, String productPrice, String productId) {

        this.imageURL = imageURL;
        this.productDesc = productDesc;
        this.productName = productName;
        this.productPrice = productPrice;
        this.productId = productId;
    }

    public Product() {

    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public String getProductDesc() {
        return productDesc;
    }

    public void setProductDesc(String productDesc) {
        this.productDesc = productDesc;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(String productPrice) {
        this.productPrice = productPrice;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getName() {
        return productName;
    }

    public int getImageResource() {
        return 0;
    }

    public String getId() {
        return productId;
    }

    public String getproductId() {
        return productId;
    }

    public byte[] getImageUrl() {
        return new byte[0];
    }

    public int getProductDescription() {
        return 0;
    }
}
