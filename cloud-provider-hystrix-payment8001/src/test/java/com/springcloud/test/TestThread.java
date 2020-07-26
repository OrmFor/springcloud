package com.springcloud.test;

import com.atguigu.springcloud.PaymentHystrixMain8001;
import com.atguigu.springcloud.controller.PaymentController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.concurrent.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = PaymentHystrixMain8001.class)
@WebAppConfiguration
public class TestThread {
    public static final int THREAD_LOOP_SIZE = 500;
    public static final int MAX_SIZE = 1000;

    @Autowired
    public PaymentController paymentController;
    @Test
    public void test(){
        BlockingQueue queue = new ArrayBlockingQueue<String>(15);

        ExecutorService executor = new ThreadPoolExecutor(MAX_SIZE, MAX_SIZE,
                0L, TimeUnit.MILLISECONDS, queue
        );


        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < THREAD_LOOP_SIZE; i++) {
            executorService.submit(() -> {
                paymentController.paymentReject();
            });
        }
        executorService.shutdown();

    }

}
