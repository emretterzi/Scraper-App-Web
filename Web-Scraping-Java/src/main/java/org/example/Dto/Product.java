package org.example.Dto;

import java.util.List;


public class Product {

    List<Product> allProductList;
    private String productName;
    private String productPrice;
    private String productLink;

    public Product(String productName, String productPrice, String productLink) {

        this.productName = productName;
        this.productPrice = productPrice;
        this.productLink = productLink;
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

    public String getProductLink() {
        return productLink;
    }

    public void setProductLink(String productLink) {
        this.productLink = productLink;
    }

    public List<Product> getAllProductsList() {
        return allProductList;
    }

    public void setAllProductsList(List<Product> allProductList) {
        this.allProductList = allProductList;
    }

    @Override
    public String toString() {
        return "Products{" +
                ", productName='" + productName + '\'' +
                ", productPrice='" + productPrice + '\'' +
                ", productLink='" + productLink + '\'' +
                '}';
    }


}
