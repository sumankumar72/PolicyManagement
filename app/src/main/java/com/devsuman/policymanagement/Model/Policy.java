package com.devsuman.policymanagement.Model;

/**
 * Created by Node1 on 10/10/2017.
 */

public class Policy {
    public String UserName;
    public String PolicyNumber;
    public String Name;
    public Float SumAssured;
    public String ModeOfPolicy;
    public String Table;
    public String Term;
    public String DOC;
    public Float Premium;


    public Policy(){}

    public Policy(String userName, String policyNumber, String name, Float sumAssured,
                  String modeOfPolicy, String table, String term, String DOC, Float premium) {
        UserName = userName;
        PolicyNumber = policyNumber;
        Name = name;
        SumAssured = sumAssured;
        ModeOfPolicy = modeOfPolicy;
        Table = table;
        Term = term;
        this.DOC = DOC;
        Premium = premium;
    }
}
