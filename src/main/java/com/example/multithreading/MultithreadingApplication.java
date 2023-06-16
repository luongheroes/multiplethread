package com.example.multithreading;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
public class MultithreadingApplication {

    public static void main(String[] args) {
        // chi su dung thead de khoi tao
        DemoThreadRun de = new DemoThreadRun();
        de.start();
        DemoThreadRun de1 = new DemoThreadRun();
        de1.start();

        // su dung Runnable de khoi tao co the mo comment phan duoi và comment phan tren de nhin ro hon
        RunnableDemo r1 = new RunnableDemo("Thread-number-1");
        r1.start();

        RunnableDemo r2 = new RunnableDemo("Thread-number-2");
        r2.start();

        RunnableDemo r3 = new RunnableDemo("Thread-number-3");
        r3.start();

    }

    public static class DemoThreadRun extends Thread {

        @Override
        public void run() {
            for (int i = 0; i < 5; i ++) {
                log.info("Thread only using thread: " + Thread.currentThread().getName() + "- thread processing " + i);
            }
            super.run();
        }
    }


    public static class RunnableDemo implements Runnable {
        private Thread t;
        private final String threadName;


        RunnableDemo(String name) {
            threadName = name;
            log.info("Creating " + threadName);
        }

        @Override
        public void run() {
            log.info("Running " + threadName);
            try {
                for (int i = 4; i > 0; i--) {
                    log.info("Thread: " + threadName + "- thread processing " + i);
                    // Let the thread sleep for a while.
                    Thread.sleep(20000);
                }
            } catch (InterruptedException e) {
                log.info("Thread " + threadName + " interrupted.");
                Thread.currentThread().interrupt();
            }
            log.info("Thread " + threadName + " exiting.");
        }

        public void start() {
            log.info("Starting " + threadName);
            if (null == t) {
                t = new Thread(this, threadName);
                t.start();
            }
        }

    }
}
