package com.learn.springboot.practice.juc;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

/**
 * 线程生命周期测试
 */
@Slf4j
public class ThreadTest {
    /**
     * 线程等待
     */
    public static class WaitingTime implements Runnable {
        @Override
        public void run() {
            while (true) {
                waitSecond(200);
            }
        }

        //线程等待多少秒
        public static final void waitSecond(long seconds) {
            try {
                TimeUnit.SECONDS.sleep(seconds);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 线程状态
     */
    public static class WaitingState implements Runnable {
        @Override
        public void run() {
            while (true) {
                synchronized (WaitingState.class) {
                    try {
                        //无超时等待
                        WaitingState.class.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    /**
     * 线程阻塞
     */
    public static class BlockedThread implements Runnable {
        @Override
        public void run() {
            synchronized (BlockedThread.class) {
                while (true) {
                    WaitingTime.waitSecond(100);
                }
            }
        }
    }


    public static void main(String[] args) {
        new Thread(new WaitingTime(), "WaitingTimeThread").start();
        new Thread(new WaitingState(), "WaitingStateThread").start();

        //BlockedThread-01线程会抢到锁，BlockedThread-02线程会阻塞
        new Thread(new BlockedThread(), "BlockedThread-01").start();
        new Thread(new BlockedThread(), "BlockedThread-02").start();
    }
}
