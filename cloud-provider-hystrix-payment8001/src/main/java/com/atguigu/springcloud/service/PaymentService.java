package com.atguigu.springcloud.service;

import cn.hutool.core.util.IdUtil;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.concurrent.TimeUnit;

@Service
public class PaymentService {
    /**
     * 正常访问
     * @param id
     * @return
     */
    public String PaymentInfo_OK(Integer id){
            return "线程池:"+Thread.currentThread().getName()+"   PaymentInfo_OK,id:"+id+"\t"+"O(∩_∩)O哈哈";
        }

    /**
     *  失败访问
     * @param id
     * @return
     */
    @HystrixCommand(fallbackMethod = "PaymentInfo_TimeOutHandler",commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "5000")  //3秒钟以内就是正常的业务逻辑
    })
    public String PaymentInfo_TimeOut(Integer id){
        int timeNumber=3;
        try {
            TimeUnit.SECONDS.sleep(timeNumber);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //int age=10/0;
        return "线程池:"+Thread.currentThread().getName()+"   PaymentInfo_TimeOut,id:"+id+"\t"+"O(∩_∩)O哈哈"+"  耗时"+timeNumber+"秒钟";
    }
    public String PaymentInfo_TimeOutHandler(Integer id) {
        return "线程池:"+Thread.currentThread().getName()+"   8001系统繁忙或者出错,请稍后再试,id:"+id+"\t"+"o(╥﹏╥)o";
    }

    //=====服务熔断
    @HystrixCommand(fallbackMethod = "paymentCircuitBreaker_fallback",commandProperties = {
            @HystrixProperty(name = "circuitBreaker.enabled",value = "true"),  //是否开启断路器
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold",value = "10"),   //请求次数
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds",value = "10000"),  //时间范围
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage",value = "60"), //失败率达到多少后跳闸
    })//表示10秒内10次请求失败率达到60%开启熔断
    public String paymentCircuitBreaker(@PathVariable("id") Integer id){
        if(id<0){
            throw new RuntimeException("****id 不能负数");
        }
        String serialNumber= IdUtil.simpleUUID();
        return Thread.currentThread().getName()+"\t"+"调用成功,流水号:"+serialNumber;
    }
    public String paymentCircuitBreaker_fallback(@PathVariable("id")Integer id){
        return "id 不能负数，请稍后重试，o(╥﹏╥)o~~~  id:"+id;
    }
}
