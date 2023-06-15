package com.example.multithreading;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
public class MultithreadingApplication {

    public static void main(String[] args) {
        RunnableDemo r1 = new RunnableDemo("Thread-number-1");
        r1.start();

        RunnableDemo r2 = new RunnableDemo("Thread-number-2");
        r2.start();

        RunnableDemo r3 = new RunnableDemo("Thread-number-3");
        r3.start();
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
