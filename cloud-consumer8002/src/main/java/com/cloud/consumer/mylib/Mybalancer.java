package com.cloud.consumer.mylib;

import org.springframework.cloud.client.ServiceInstance;

import java.util.List;

public interface Mybalancer {
    ServiceInstance instances(List<ServiceInstance> serviceInstances);
}
