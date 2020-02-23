/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package worldwine.model;

import worldwine.jpa.model.Product;

/**
 *
 * @author Chutikan
 */
public class LineItem {
    Product product;
    double salePrice;
    int quantity;

    public LineItem() {
    }
    public LineItem(Product product) {
        this(product,1);
    }

    public LineItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
        this.salePrice = product.getPrice();
    }
    public double getTotalPrice(){
        return this.quantity*this.salePrice;
    }
    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public double getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(double salePrice) {
        this.salePrice = salePrice;
    }

    public int getQuantity() {
        return quantity;
    }


    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    
}
