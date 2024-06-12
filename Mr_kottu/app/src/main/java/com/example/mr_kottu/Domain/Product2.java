package com.example.mr_kottu.Domain;

// Product.java
public class Product2 extends Product {
    private String imageURL ;

    private  String productDesc;

    private String productName;


    private String productPrice;
    private String productId;


    public Product2(String imageURL, String productDesc, String productName, String productPrice,String productId) {

        this.imageURL = imageURL;
        this.productDesc = productDesc;
        this.productName = productName;
        this.productPrice = productPrice;
        this.productId = productId;
    }



    public String getproductId() {
        return productId;
    }

    public void setProductId(String documentId) {
        this.productId = documentId;
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

    @Override
    public void setProductPrice(String productPrice) {
        this.productPrice = productPrice;
    }

    public Product2() {

    }


    public void add(Product2 product2) {
    }
}
