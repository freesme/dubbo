package org.apache.dubbo.springboot.demo.consumer.controller;

import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.dubbo.springboot.demo.AsyncRemoteService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/f2f")
public class Face2FaceController {

    @DubboReference
    AsyncRemoteService asyncRemoteService;

    @GetMapping("/hello")
    public String hello(String name) {
        // 调用直接返回CompletableFuture
        CompletableFuture<String> future = asyncRemoteService.sayHello("async call request");
        // 增加回调
        future.whenComplete((v, t) -> {
            if (t != null) {
                t.printStackTrace();
            } else {
                System.out.println("Response: " + v);
            }
        });
// 早于结果输出
        System.out.println("Executed before response return.");
        return "success";
    }
}
