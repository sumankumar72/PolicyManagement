package com.devsuman.policymanagement.Model;

/**
 * Created by Node1 on 10/10/2017.
 */

public class Policy {
    private String UserName;
    private String PolicyNumber;
    private String Name;
    private Float SumAssured;
    private String ModeOfPolicy;
    private String Table;
    private String Term;
    private String DOC;
    private Float Premium;

    public Policy(){

    }

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

    public void setUserName(String userName) {
        UserName = userName;
    }

    public void setPolicyNumber(String policyNumber) {
        PolicyNumber = policyNumber;
    }

    public void setName(String name) {
        Name = name;
    }

    public void setSumAssured(Float sumAssured) {
        SumAssured = sumAssured;
    }

    public void setModeOfPolicy(String modeOfPolicy) {
        ModeOfPolicy = modeOfPolicy;
    }

    public void setTable(String table) {
        Table = table;
    }

    public void setTerm(String term) {
        Term = term;
    }

    public void setDOC(String DOC) {
        this.DOC = DOC;
    }

    public void setPremium(Float premium) {
        Premium = premium;
    }

    public String getUserName() {
        return UserName;
    }

    public String getPolicyNumber() {
        return PolicyNumber;
    }

    public String getName() {
        return Name;
    }

    public Float getSumAssured() {
        return SumAssured;
    }

    public String getModeOfPolicy() {
        return ModeOfPolicy;
    }

    public String getTable() {
        return Table;
    }

    public String getTerm() {
        return Term;
    }

    public String getDOC() {
        return DOC;
    }

    public Float getPremium() {
        return Premium;
    }
}
