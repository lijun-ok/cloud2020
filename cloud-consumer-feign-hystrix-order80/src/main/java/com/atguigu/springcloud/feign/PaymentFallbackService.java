package com.atguigu.springcloud.feign;

import org.springframework.stereotype.Component;

@Component
public class PaymentFallbackService implements PaymentHystrixFeignService {
    @Override
    public String payment_OK(Integer id) {
        return "------PaymentFallbackService fall back-payment_OK,o(╥﹏╥)o";
    }

    @Override
    public String payment_TimeOut(Integer id) {
        return "------PaymentFallbackService fall back-payment_TimeOut,o(╥﹏╥)o";
    }
}
