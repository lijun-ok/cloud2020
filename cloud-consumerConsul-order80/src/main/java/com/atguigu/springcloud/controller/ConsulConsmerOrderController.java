package com.atguigu.springcloud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class ConsulConsmerOrderController {


    @Autowired
    private RestTemplate restTemplate;

    public static final String CONSUL_URL="http://consul-provider-payment";

    @GetMapping("/consumer/payment/consul")
    public String getProductProviderInfo(){
        return restTemplate.getForObject(CONSUL_URL+"/payment/consul",String.class);
    }

}
