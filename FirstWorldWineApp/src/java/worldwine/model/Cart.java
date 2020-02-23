/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package worldwine.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpSession;
import worldwine.jpa.model.Product;

/**
 *
 * @author Chutikan
 */
public class Cart {

    private Map<String, LineItem> cart;
    
    public Cart() {
        cart = new HashMap();
    }
    
    public void add(Product p) {
        LineItem line = cart.get(p.getProductname());
        if (line == null) {
            cart.put(p.getProductname(), new LineItem(p));
        } else {
            line.setQuantity(line.getQuantity() + 1);
        }
        getTotalPrice2();
    }
        public void drop(Product p) {
        LineItem line = cart.get(p.getProductname());
            line.setQuantity(line.getQuantity() - 1);
            if(line.getQuantity() == 0){
               remove(p);
            }
        
    }
    
    public void remove(Product p) {
        this.remove(p.getProductname());
    }
    
    public void remove(String productName) {
        cart.remove(productName);
    }
    
    public BigDecimal getTotalPrice(){
        BigDecimal sum = BigDecimal.ZERO ;
        Collection<LineItem> lineItems = cart.values();
        for (LineItem lineItem : lineItems) {
//            sum = sum.add( lineItem.getTotalPrice());
        }
        return sum;
    }
        public Double getTotalPrice2(){
        double sum = 0;
        Collection<LineItem> lineItems = cart.values();
                for (LineItem lineItem : lineItems) {
            sum +=lineItem.getTotalPrice();
        } 
        return sum;
    }
//             public Double setTotalPrice2(double totalprice){
//               return  totalprice;
//    }
    
    public int getTotalQuantity(){
        int sum =0;
        Collection<LineItem> lineItems = cart.values();
        for (LineItem lineItem : lineItems) {
            sum += lineItem.getQuantity();
            }
        return sum;
    }
    
    public List<LineItem> getLineItems(){
        return new ArrayList(cart.values());
    }
}
