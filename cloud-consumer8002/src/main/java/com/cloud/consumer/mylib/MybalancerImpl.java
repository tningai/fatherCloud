package com.cloud.consumer.mylib;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Component
public class MybalancerImpl implements Mybalancer{

    private final AtomicInteger atomicInteger = new AtomicInteger(0);

    private final int incrementInt(){
        int current,next;
        do{
            current = atomicInteger.get();
            next = current >= Integer.MAX_VALUE ? 0 : atomicInteger.incrementAndGet();
        }while (!atomicInteger.compareAndSet(current, next));
        return next;
    }

    @Override
    public ServiceInstance instances(List<ServiceInstance> serviceInstances) {
        int index = incrementInt() % serviceInstances.size();
        return serviceInstances.get(index);
    }
}
