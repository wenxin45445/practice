package com.meng.webdemo;

public class LambdaTest{

    public static void main(String[] args) {
        LambdaTest lambdaTest = new LambdaTest();
        System.out.println(lambdaTest.testWork());
    }
    public Object testWork(){
        DoWorkBase startWork = new DoWorkBase();
        return startWork.getWorkResult();
    }

    private static void lambdaTest() {
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

    @FunctionalInterface
    interface WorkService<T>{
        T doWork1();
    }

    class DoWorkBase{
        public Object getWorkResult(){
            return doOtherWork("doWork1", () -> doWork3());
        }

        public String doWork3(){
            return "doWork3";
        }

        public Object doOtherWork(String workName, WorkService workService){
            System.out.println("i am doing: " + workName);
            return workService.doWork1();
        }
    }
}
