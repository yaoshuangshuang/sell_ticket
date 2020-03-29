package com.atguigu;

@FunctionalInterface
interface Demo{
    public int add(int a ,int b);

    default int sub(int a , int b)
    {
        return a - b ;
    }
    public static int div(int a ,int b){
        return a/b;
    }

}
public class LambdaExpress {
    public static void main(String[] args) {
        Demo demo = (a,b) -> {
//        不使用线程，所以不用new
            System.out.println("Hello,today");

            return a + b ;
        };
        System.out.println( demo.add(88,88));
        System.out.println( demo.sub(88,88));
        //static 静态方法，使用类名调用
        System.out.println( Demo.div(88,88));

    }
}
