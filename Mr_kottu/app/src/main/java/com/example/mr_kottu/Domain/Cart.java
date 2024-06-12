package com.example.mr_kottu.Domain;

// Product.java
public class Cart {
  private String imageURL ;

  private  String productDesc;

  private String productName;


  private String productPrice;


    private String qty;


    public Cart(String imageURL, String productDesc, String productName, String productPrice, String qty) {
        this.imageURL = imageURL;
        this.productDesc = productDesc;
        this.productName = productName;
        this.productPrice = productPrice;


        this.qty =qty;

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

    public String getQty() {
        return qty;
    }

    public void setQty(String qty) {
        this.qty = qty;
    }

    public Cart() {
    }

    public int getProductQty() {
        return 0;
    }

    public String getproductId() {
        return null;
    }
}
