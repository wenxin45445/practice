package com.meng.webdemo;

public class LambdaTest{

    public static void main(String[] args) {
        String pp = "pangpang";
        LambdaTest lambdaTest = new LambdaTest();
        lambdaTest.getS(pp, () -> {
            System.out.println("pangpang inner");
            return getOb();
        });

    }


    public void getS(String name, FuncInter funcInter){

        funcInter.funInter();
    }

    public static Object  getOb(){
        return null;
    }
}
