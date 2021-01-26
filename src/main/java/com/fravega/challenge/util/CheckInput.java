package com.fravega.challenge.util;

public class CheckInput {

    public static boolean isValid(String input) {
        try{
            double isNum = Double.parseDouble(input);
            return true;
        } catch(Exception e) {
            if(input.toCharArray().length == 1) {
                return false;
            }else {
                return false;
            }
        }
    }
}
