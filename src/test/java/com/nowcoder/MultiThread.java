package com.nowcoder;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Administrator on 2017/4/14.
 */

class MyThread extends Thread {

    private int tid;

    @Override
    public void run() {
        try {
            for (int i = 0; i < 10; ++i) {
                System.out.println(String.format("T%d:%d", tid, i));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public MyThread(int tid) {
        this.tid = tid;
    }

}


public class MultiThread {

    public static void testThread() {
        for (int i = 0; i < 10; ++i) {
            //new MyThread(i).start();
        }

        for (int i = 0; i < 10; ++i) {
            final int tid = i;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        for (int i = 0; i < 10; ++i) {
                            System.out.println(String.format("T22%d:%d", tid, i));
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }

    }


    private static Object obj = new Object();

    public static void testSynchronized1() {
        synchronized (obj) {
            try {
                for (int i = 0; i < 10; ++i) {
                    Thread.sleep(1000);
                    System.out.println(String.format("T3:%d", i));
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void testSynchronized2() {
        synchronized (obj) {
            try {
                for (int i = 0; i < 10; ++i) {
                    Thread.sleep(1000);
                    System.out.println(String.format("T4:%d", i));
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    public static void testSynchronized() {
        for (int i = 0; i < 10; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    testSynchronized1();
                    testSynchronized2();
                }
            }).start();
        }
    }


    static class Producer implements Runnable {

        private BlockingQueue<String> q;

        public Producer(BlockingQueue<String> q) {
            this.q = q;
        }


        @Override
        public void run() {
            try {
                for (int i = 0; i < 10; i++) {
                    Thread.sleep(1000);
                    q.put(String.valueOf(i));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    static class Consumer implements Runnable {
        private BlockingQueue<String> q;

        public Consumer(BlockingQueue<String> q) {
            this.q = q;
        }

        @Override
        public void run() {
            try {
                while (true) {
                    System.out.println(Thread.currentThread().getName() + ":" + q.take());
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }




    public static void testBlockingQueue() {
        BlockingQueue<String> q = new ArrayBlockingQueue<String>(10);
        new Thread(new Producer(q)).start();
        new Thread(new Consumer(q)).start();

    }


    private static int counter = 0;
    private static AtomicInteger atomicInteger = new AtomicInteger(0);
    public static void sleep(int mills) {
        try{
            Thread.sleep(new Random().nextInt(mills));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void testWithAtomic() {
        for (int i = 0; i < 10; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    sleep(1000);
                    for (int j = 0; j < 10; j++) {
                        System.out.println(atomicInteger.incrementAndGet());
                    }
                }
            }).start();
        }
    }

    public static void testExecutor() {
        ExecutorService service = Executors.newSingleThreadExecutor();
        service.submit(new Runnable() {
            @Override
            public void run() {

            }
        });
    }




    public static void main(String[] args) {

        Object a = new Object();

        Map<String,Object> map = new HashMap<String,Object>();
        Map<String,Object> map1 = new Hashtable<String,Object>();
        Map<String,Object> map2 = new ConcurrentHashMap<String,Object>();

//        testSynchronized();
//        testBlockingQueue();
        testWithAtomic();
    }


}
