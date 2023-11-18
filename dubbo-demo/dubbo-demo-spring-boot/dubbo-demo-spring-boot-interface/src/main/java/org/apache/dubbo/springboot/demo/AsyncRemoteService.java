package org.apache.dubbo.springboot.demo;

import java.util.concurrent.CompletableFuture;

public interface AsyncRemoteService {
    CompletableFuture<String> sayHello(String name);
}
