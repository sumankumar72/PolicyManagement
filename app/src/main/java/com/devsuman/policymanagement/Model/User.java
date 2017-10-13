package com.devsuman.policymanagement.Model;

/**
 * Created by Node1 on 10/10/2017.
 */

public class User {
    private String UserName;
    private String Password;
    private String FullName;

    public User(){
    }

    public User(String userName, String password, String fullName) {
        UserName = userName;
        Password = password;
        FullName = fullName;
    }

    public String getUserName() {
        return UserName;
    }

    public String getPassword() {
        return Password;
    }

    public String getFullName() {
        return FullName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public void setFullName(String fullName) {
        FullName = fullName;
    }
}
