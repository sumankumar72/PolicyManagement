package com.devsuman.policymanagement.Model;

/**
 * Created by Node1 on 10/10/2017.
 */

public class User {
    public String UserName;
    public String Password;
    public String FullName;

    public User(){
    }

    public User(String userName, String password, String fullName) {
        UserName = userName;
        Password = password;
        FullName = fullName;
    }
}
