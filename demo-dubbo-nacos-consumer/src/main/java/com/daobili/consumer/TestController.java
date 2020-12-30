package com.daobili.consumer;

import com.daobili.api.DemoService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author bamaw
 * @date 2020-12-29  23:31
 */
@RestController
public class TestController {


    @Reference(
            version = "1.0.0",
            interfaceClass = DemoService.class,
            cluster = "failfast"
    )
    private DemoService demoService;

    @GetMapping("/greet")
    public String greet(String name) {
        return demoService.greet(name);
    }

}
