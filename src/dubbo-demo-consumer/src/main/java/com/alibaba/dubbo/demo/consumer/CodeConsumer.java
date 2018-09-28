package com.alibaba.dubbo.demo.consumer;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ReferenceConfig;
import com.alibaba.dubbo.demo.DemoService;

public class CodeConsumer {
    public static void main(String[] args) {
        ApplicationConfig applicationConfig = new ApplicationConfig();
        applicationConfig.setName("demo-consumer");
        ReferenceConfig<DemoService> referenceConfig = new ReferenceConfig<DemoService>();
        referenceConfig.setInterface(DemoService.class);
        referenceConfig.setVersion("1.0");

        referenceConfig
                .setUrl("dubbo://xxx.xxx.xxx.xxx:20168/com.vdian.test.dubbo.support.HelloService");

        referenceConfig.setApplication(applicationConfig);

        DemoService hello = referenceConfig.get();
        try {
            String result = hello.sayHello("aa");
            System.out.println(result);
        } catch (Exception e) {
            System.out.print("1");
        }
    }
}
