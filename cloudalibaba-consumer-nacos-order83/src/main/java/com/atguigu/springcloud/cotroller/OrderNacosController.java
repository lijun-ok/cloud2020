package com.atguigu.springcloud.cotroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class OrderNacosController {

    @Autowired
    private RestTemplate restTemplate;
    @Value("${service-url.nacos-user-service}")
    private String url;
    @GetMapping("/consumer/payment/nacos/{id}")
    public String getPayment(@PathVariable("id") Long id){
       return restTemplate.getForObject(url+"/payment/nacos/"+id,String.class);
    }
}
