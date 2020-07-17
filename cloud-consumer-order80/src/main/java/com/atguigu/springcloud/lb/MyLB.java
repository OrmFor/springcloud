package com.atguigu.springcloud.lb;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.stereotype.Component;

import java.text.MessageFormat;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;


@Component
@Slf4j
public class MyLB implements LoadBalancer {

    private AtomicInteger automicInteger = new AtomicInteger(0);

    private final int getAndIncrement(){
        int current;
        int next;
        do{
          current = this.automicInteger.get();
          next = current >= Integer.MAX_VALUE ? 0 : current+1;
        }while (!this.automicInteger.compareAndSet(current,next));
        log.info(MessageFormat.format("*******第{0}次访问*********",next));
        return next;
    }

    @Override
    public ServiceInstance instances(List<ServiceInstance> serviceInstances) {
        int index = getAndIncrement() % serviceInstances.size();
        return serviceInstances.get(index);
    }
}
