package com.example.mr_kottu;

public class EmailHolder {
    private static String userEmail;

    private static String userMobile;

    public static String getUserEmail() {
        return userEmail;
    }

    public static void setUserEmail(String userEmail) {
        EmailHolder.userEmail = userEmail;
    }

    public static String getUserMobile() {
        return userMobile;
    }

    public static void setUserMobile(String userMobile) {
        EmailHolder.userMobile = userMobile;
    }


    public static String getuserMobile() {
        return null;
    }
}
