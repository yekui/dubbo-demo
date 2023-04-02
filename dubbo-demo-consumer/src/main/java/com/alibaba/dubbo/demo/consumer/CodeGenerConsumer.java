package com.alibaba.dubbo.demo.consumer;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ReferenceConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import com.alibaba.dubbo.config.utils.ReferenceConfigCache;
import com.alibaba.dubbo.rpc.service.GenericService;
import com.alibaba.fastjson.JSON;

public class CodeGenerConsumer {

    public static void main(String[] args) {
        // test
        int i = 0;
        int a = 0;
        int b = 0;
        while (true) {
            ApplicationConfig applicationConfig = new ApplicationConfig();
            applicationConfig.setName("demo-consumer");

            RegistryConfig registryConfig = new RegistryConfig();
            registryConfig.setAddress("zookeeper://10.1.132.160:2181");

            ReferenceConfig<GenericService> referenceConfig = new ReferenceConfig<GenericService>();
            referenceConfig.setInterface("com.alibaba.dubbo.demo.DemoService");
            referenceConfig.setVersion("1.0.0");
            referenceConfig.setGeneric(true);
            referenceConfig.setCheck(true);
            referenceConfig.setTimeout(30 * 1000);

            referenceConfig.setApplication(applicationConfig);
            referenceConfig.setRegistry(registryConfig);

            Object result = null;
            try {
            //
            ReferenceConfigCache cache = ReferenceConfigCache.getCache();
            GenericService genericService = cache.get(referenceConfig);

            // GenericService genericService = referenceConfig.get();

            result = genericService.$invoke("sayHello", new String[] { "java.lang.String" }, new Object[] { "dengkui" });
        } catch (Exception e) {
            System.out.println(e);
        }

            i++;
            System.out.println(JSON.toJSONString(result));
            try {
                Thread.sleep(3 * 1000);
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
            System.out.println("i : --> " + i);
        }
    }
}
