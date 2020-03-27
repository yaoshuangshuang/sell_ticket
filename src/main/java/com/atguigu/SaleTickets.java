package com.atguigu;


import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Tickets{
    //假设50张票
    private  int number = 50 ;
    private Lock lock = new ReentrantLock() ;
    public void sale() {
        lock.lock();
        try {
            if(number > 0 )
            {
                System.out.println(Thread.currentThread().getName() +"\t卖出第：" + (number--) + "\t 还剩下：" +number );
            }
        } finally {
              lock.unlock();
        }
    }
}
public class SaleTickets {
//线程，操作，资源类
    public static void main(String[] args) {
        final Tickets ticket = new Tickets(); //资源类
        new Thread(new Runnable() {
            // new 多线程
            @Override
            public void run() {
                for (int i = 0; i < 55; i++) {
                    ticket.sale();
                    //内部类中不能引用本地变量，需要声明为常量？
                }
            }
        },
                "A").start();
        new Thread(new Runnable() {
            // new 多线程
            @Override
            public void run() {
                for (int i = 0; i < 55; i++) {
                    ticket.sale();
                }
            }
        },"B").start();
        new Thread(new Runnable() {
            // new 多线程
            @Override
            public void run() {
                for (int i = 0; i < 55; i++) {
                    ticket.sale();
                }
            }
        },"C").start();

    }
}
