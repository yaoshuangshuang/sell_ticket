package com.atguigu;
class  AirConditioning {

    /*
    待办:增加俩个线程
     */
    private int number = 0;
    public synchronized void increment() throws Exception {
      // 判断
//        将if 换为 while ，if 是判断，while是循环
        //注意： wait 常和 while 一块使用,只要是wait,await使用while避免虚假唤醒

        while (number != 0) {
            this.wait();//线程停止交出控制权
        }
        //业务
        number++;
        System.out.println(Thread.currentThread().getName() + "\t"
        +number );
        //通知
        this.notifyAll();
    }

    public synchronized void decrement() throws Exception {
        while (number == 0) {
            this.wait();//线程停止交出控制权
        }
        //业务
        number--;
        System.out.println(Thread.currentThread().getName() + "\t"
                +number );
        //通知
        this.notifyAll();
    }
}
public class ProdConsumer {

    //初始值为0的变量，一个线程加1，另外一个减1
    public static void main(String[] args) {
        AirConditioning airConditioning = new AirConditioning();
        new Thread(() -> {

            for (int i = 1; i < 10; i++) {
                try {
                    airConditioning.increment();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        }, "A").start();
        //lambda 代替 匿名内部类 ，拷贝小括号，写死右键头，落地大括号
        new Thread(() -> {
            for (int i = 1; i < 10; i++) {
                try {
                    airConditioning.decrement();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, "B").start();
        //增加两个线程
        new Thread(() -> {
            for (int i = 1; i < 10; i++) {
                try {
                    airConditioning.increment();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, "C").start();

        new Thread(() -> {
            for (int i = 1; i < 10; i++) {
                try {
                    airConditioning.decrement();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, "D").start();

    }
}
