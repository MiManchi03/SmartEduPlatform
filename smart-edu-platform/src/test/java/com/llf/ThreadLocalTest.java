package com.llf;

public class ThreadLocalTest {
    private static ThreadLocal<String> threadLocal = new ThreadLocal<>();

    public static void main(String[] args) {
        threadLocal.set("Main Message");//当前主线程main存储
        //创建线程
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + ":" + threadLocal.get());//这里打印的null，因为不是这个线程存储的
            }
        }).start();
        System.out.println(Thread.currentThread().getName() + ":" + threadLocal.get());//只能当前主线程main获取
    }
}
