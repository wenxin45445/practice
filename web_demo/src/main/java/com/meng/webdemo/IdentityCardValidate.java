package com.meng.webdemo;

import org.springframework.util.StringUtils;

public class IdentityCardValidate {

    private int[] calculateCode = {7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2};
    private String[] validateCodeStr = {"1", "0", "X", "9", "8", "7", "6", "5", "4", "3", "2"};

    public static String temp = "411402199005087635";
    public static String my = "411402198905277636";

    public static void main(String[] args) {
        System.out.println(new IdentityCardValidate().validateCardEighteenCRC(my));
    }

    public boolean validateCardEighteenCRC(String idNo){
        if (StringUtils.isEmpty(idNo) || idNo.length() < 18) {
            return false;
        }

        int sum = 0;
        String[] bits = idNo.split("");
        for (int i = 0; i < bits.length -1 ; i++){
            int noPerBit = Integer.parseInt(bits[i]);
            sum += noPerBit * calculateCode[i];
        }

        int remainder = sum % 11;
        if (validateCodeStr[remainder].equals(bits[bits.length -1])){
            return true;
        }

        return false;
    }
}
