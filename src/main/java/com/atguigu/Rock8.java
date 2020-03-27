package com.atguigu;

import java.util.concurrent.TimeUnit;
/*
待办 todo
手机发短信和邮件
1， email -> sms
2,  email -> sms
3,  false: email -> hello ,true : hello -> email  锁的是什么？
4,  email -> sms   true : sms —> emasil
5,  email -> sms
6,  email -> sms
7,  sms -> email
8,  sms -> email
  */

class Phone
{
    public static synchronized void sendemail () throws Exception {
        TimeUnit.SECONDS.sleep(3);
        //时间单位可以选择
//        Thread.sleep(3);
        System.out.println("Email");

    }
    public  synchronized void sendsms (){
        System.out.println("SMS");
}
    public void hello(){
        System.out.println("hello");
    }
public static class Lock8 {
    public static void main(String[] args) {
//线程，操作，资源类
        //Inner classes cannot have static declarations??
        Phone phone = new Phone();
        Phone phone1 = new Phone();
        new Thread(() -> {

            try {
                phone.sendemail();
            } catch (Exception e) {
                e.printStackTrace();
            }

        },"A").start();

        new Thread(() -> {

            phone1.sendsms();

        },"B").start();
       }
    }
}
