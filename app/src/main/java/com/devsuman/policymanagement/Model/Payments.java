package com.devsuman.policymanagement.Model;

import java.util.Date;

/**
 * Created by Node1 on 10/10/2017.
 */

public class Payments {
    private String PolicyNumber;
    private String UserName;
    private Float Amount;
    private String PaymentDate;

    public Payments(){}

    public Payments(String policyNumber, String userName, Float amount, String paymentDate) {
        PolicyNumber = policyNumber;
        UserName = userName;
        Amount = amount;
        PaymentDate = paymentDate;
    }

    public String getPolicyNumber() {
        return PolicyNumber;
    }

    public String getUserName() {
        return UserName;
    }

    public Float getAmount() {
        return Amount;
    }

    public String getPaymentDate() {
        return PaymentDate;
    }


    public void setPolicyNumber(String policyNumber) {
        PolicyNumber = policyNumber;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public void setAmount(Float amount) {
        Amount = amount;
    }

    public void setPaymentDate(String paymentDate) {
        PaymentDate = paymentDate;
    }
}
