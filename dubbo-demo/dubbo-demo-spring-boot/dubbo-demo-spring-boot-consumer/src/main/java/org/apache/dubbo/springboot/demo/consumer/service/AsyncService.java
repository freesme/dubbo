package org.apache.dubbo.springboot.demo.consumer.service;

import org.apache.dubbo.config.annotation.DubboService;
import org.apache.dubbo.springboot.demo.AsyncRemoteService;

import java.util.concurrent.CompletableFuture;

@DubboService
public class AsyncService implements AsyncRemoteService {
    @Override
    public CompletableFuture<String> sayHello(String name) {
        return CompletableFuture.supplyAsync(() -> {
            System.out.println(name);
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "async response from provider: Hello " + name;
        });
    }
}
