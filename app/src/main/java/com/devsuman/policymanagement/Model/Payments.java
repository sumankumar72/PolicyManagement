package com.devsuman.policymanagement.Model;

import java.util.Date;

/**
 * Created by Node1 on 10/10/2017.
 */

public class Payments {
    public String PolicyNumber;
    public String UserName;
    public Float Amount;
    public Date PaymentDate;

    public Payments(){}

    public Payments(String policyNumber, String userName, Float amount, Date paymentDate) {
        PolicyNumber = policyNumber;
        UserName = userName;
        Amount = amount;
        PaymentDate = paymentDate;
    }
}
