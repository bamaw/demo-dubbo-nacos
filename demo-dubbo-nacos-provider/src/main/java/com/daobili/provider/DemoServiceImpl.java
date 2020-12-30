package com.daobili.provider;

import com.daobili.api.DemoService;
import org.apache.dubbo.config.annotation.Service;

/**
 * @author bamaw
 * @date 2020-12-29  23:08
 */
@Service(
        version = "1.0.0",
        interfaceClass = DemoService.class,
        cluster = "failfast",
        loadbalance = "roundrobin"
)
public class DemoServiceImpl implements DemoService {

    @Override
    public String greet(String name) {
        return "hello, "+ name;
    }
}
