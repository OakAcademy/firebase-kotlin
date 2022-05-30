package com.techmania.testfirebasejava;

public class Users {

    String userId;
    String userName;
    int userAge;
    String userEmail;

    public Users() {
    }

    public Users(String userId, String userName, int userAge, String userEmail) {
        this.userId = userId;
        this.userName = userName;
        this.userAge = userAge;
        this.userEmail = userEmail;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getUserAge() {
        return userAge;
    }

    public void setUserAge(int userAge) {
        this.userAge = userAge;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }
}
