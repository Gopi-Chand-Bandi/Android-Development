package com.abc.justjob.ApiFile.LoginRegisterApi;

public class Users {
    private int UserId;
    private String UserName;
    private String UserEmail;
    private String UserPassword;

    public Users(String userName, String userEmail, String userPassword) {
        UserName = userName;
        UserEmail = userEmail;
        UserPassword = userPassword;
    }

    public Users(int userId, String userName, String userEmail) {
        UserId = userId;
        UserName = userName;
        UserEmail = userEmail;
    }

    public Users(int userId,String userEmail){
        UserId=userId;
        UserEmail=userEmail;
    }

    public Users(int userId, String userName, String userEmail, String userPassword) {
        UserId = userId;
        UserName = userName;
        UserEmail = userEmail;
        UserPassword = userPassword;
    }

    public int getUserId() {
        return UserId;
    }

    public String getUserName() {
        return UserName;
    }

    public String getUserEmail() {
        return UserEmail;
    }

    public String getUserPassword() {
        return UserPassword;
    }
}
