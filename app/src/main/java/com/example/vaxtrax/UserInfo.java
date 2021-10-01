package com.example.vaxtrax;

public class UserInfo {
    private static final UserInfo instance = new UserInfo();
    private String firstName = "firstName";
    private String lastName = "lastName";
    private int age = 1337;

    public static UserInfo getInstance() {
        return instance;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public void setAge(int age) {
        this.age = age;
    }

    public String getFirstName(){
        return firstName;
    }
    public String getLastName(){
        return lastName;
    }
    public int getAge(){
        return age;
    }
}
