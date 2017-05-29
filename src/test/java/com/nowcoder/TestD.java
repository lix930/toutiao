package com.nowcoder;

/**
 * 测试 sleep（）和 wait（）的区别
 * Created by Administrator on 2017/4/16.
 */
public class TestD {

    public static void main(String[] args) {

        new Thread(new Thread1()).start();
        try {
            Thread.sleep(5000);
        } catch (Exception e) {
            e.printStackTrace();
        }

        new Thread(new Thread2()).start();
    }

    private static class Thread1 implements Runnable {
        @Override
        public void run() {
            synchronized (TestD.class) {
                System.out.println("enter thread1...");
                System.out.println("thread1 is waiting....");
                try {
                    TestD.class.wait();
                } catch (Exception e) {
                    e.printStackTrace();
                }

                System.out.println("thread1 is going on ...");
                System.out.println("thread1 is over!!!!");
            }
        }
    }


    private static class Thread2 implements Runnable {
        @Override
        public void run() {
            synchronized (TestD.class) {
                System.out.println("enter thread2...");
                System.out.println("thread2 is waiting....");

                //TestD.class.notify();

                try {
                    Thread.sleep(5000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                System.out.println("thread2 is going on ...");
                System.out.println("thread2 is over!!!!");
            }
        }
    }

}
