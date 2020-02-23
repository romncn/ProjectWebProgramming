/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package worldwine.model;

/**
 *
 * @author Chutikan
 */
public class Payment {
    private String method;
    private String numberpayment;
    private String namecard;
    private int cvv;
    private String address;

    public Payment() {
    }

    public Payment(String method, String numberpayment, String namecard, int cvv, String address) {
        this.method = method;
        this.numberpayment = numberpayment;
        this.namecard = namecard;
        this.cvv = cvv;
        this.address = address;
    }

    public Payment(String method, String numberpayment, String namecard, int cvv) {
        this.method = method;
        this.numberpayment = numberpayment;
        this.namecard = namecard;
        this.cvv = cvv;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getNumberpayment() {
        return numberpayment;
    }

    public void setNumberpayment(String numberpayment) {
        this.numberpayment = numberpayment;
    }

    public String getNamecard() {
        return namecard;
    }

    public void setNamecard(String namecard) {
        this.namecard = namecard;
    }

    public int getCvv() {
        return cvv;
    }

    public void setCvv(int cvv) {
        this.cvv = cvv;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    
    
    
}
