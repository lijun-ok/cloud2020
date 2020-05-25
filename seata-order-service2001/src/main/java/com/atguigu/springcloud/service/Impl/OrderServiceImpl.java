package com.atguigu.springcloud.service.Impl;

import com.atguigu.springcloud.dao.OrderDao;
import com.atguigu.springcloud.domain.Order;
import com.atguigu.springcloud.service.AccountService;
import com.atguigu.springcloud.service.OrderService;
import com.atguigu.springcloud.service.StorageService;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.extern.slf4j.Slf4j;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

    @Autowired
   private OrderDao orderDao;
    @Autowired
    private AccountService accountService;
    @Autowired
    private StorageService storageService;

    /**
     * 创建订单->调用库存服务扣减库存->调用账户服务扣减账户余额->修改订单状态
     * @param order
     */
    @GlobalTransactional(name = "fsp-create-order",rollbackFor = Exception.class)
    @Override
    public void create(Order order) {
        log.info("*****开始创建订单*****");
        orderDao.create(order);
        log.info("*****订单创建完成*****");
        log.info("*****开始扣减库存*****");
        storageService.decrease(order.getProductId(),order.getCount());
        log.info("*****扣减库存完成*****");
        log.info("*****扣减账户余额*****");
        accountService.decrease(order.getUserId(),order.getMoney());
        log.info("*****扣减账户余额完成*****");
        log.info("*****开始修改订单状态*****");
        orderDao.update(order.getUserId(),0);
        log.info("*****修改订单状态完成*****");
        log.info("*****下单结束*****");
    }

    @Override
    public void update(Long userId, Integer status) {

    }
}
