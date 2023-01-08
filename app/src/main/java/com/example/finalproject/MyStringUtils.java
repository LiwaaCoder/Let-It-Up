package com.example.finalproject;

public class MyStringUtils
{

    public static String getTimeBySeconds(long seconds) {
        String str = "";
        long t;

        if (seconds > 3600) {
            t = seconds / 3600;
            if (t < 10) {
                str += "0" + t + ":";
            }
            else {
                str += t + ":";
            }
        }
        seconds %= 3600;

        if (seconds > 60) {
            t = seconds / 60;
            if (t < 10) {
                str += "0" + t + ":";
            }
            else {
                str += t + ":";
            }
        }
        else {
            str += "00:";
        }

        seconds %= 60;
        if (seconds < 10) {
            str += "0" + seconds;
        }
        else {
            str += "" + seconds;
        }

        return str;
    }
}