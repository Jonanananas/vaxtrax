package com.example.vaxtrax;

public class UserInfo {
    private static final UserInfo instance = new UserInfo();
    private static String firstName = "firstName";
    private static String lastName = "lastName";
    private static int age = 1337;

    public static UserInfo getInstance() {
        return instance;
    }

    public static void setFirstName(String firstName) {
        UserInfo.firstName = firstName;
    }
    public static void setLastName(String lastName) {
        UserInfo.lastName = lastName;
    }
    public static void setAge(int age) {
        UserInfo.age = age;
    }

    public static String getFirstName(){
        return firstName;
    }
    public static String getLastName(){
        return lastName;
    }
    public static int getAge(){
        return age;
    }
}
