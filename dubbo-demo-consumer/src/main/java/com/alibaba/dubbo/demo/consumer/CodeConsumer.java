package com.alibaba.dubbo.demo.consumer;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ReferenceConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import com.alibaba.dubbo.demo.DemoService;

public class CodeConsumer {

    public static void main(String[] args) {
        ApplicationConfig applicationConfig = new ApplicationConfig();
        applicationConfig.setName("demo-consumer");
        RegistryConfig registryConfig = new RegistryConfig();
        registryConfig.setAddress("zookeeper://10.1.132.160:2181");

        ReferenceConfig<DemoService> referenceConfig = new ReferenceConfig<DemoService>();
        referenceConfig.setInterface(DemoService.class);
        referenceConfig.setVersion("1.0.0");
        referenceConfig.setTimeout(30 * 1000);

        referenceConfig.setApplication(applicationConfig);
        referenceConfig.setRegistry(registryConfig);
        int i = 0;

        while (true) {
            try {
                DemoService hello = referenceConfig.get();
                String result = hello.sayHello("aa");
                System.out.println(result);
                i++;
                System.out.println("i = : " + i);
                Thread.sleep(3 * 1000);
            } catch (Exception e) {
                System.out.print(e.getMessage());
            }
        }
    }
}
