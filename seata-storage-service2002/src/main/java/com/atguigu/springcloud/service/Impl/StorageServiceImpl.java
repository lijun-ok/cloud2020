package com.atguigu.springcloud.service.Impl;

import com.atguigu.springcloud.dao.StorageDao;
import com.atguigu.springcloud.service.StorageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class StorageServiceImpl implements StorageService {

    @Autowired
     StorageDao storageDao;

    @Override
    public void decrease(Long productId, Integer count) {
        log.info("*********开始扣减库存*******");
        storageDao.decrease(productId,count);
        log.info("**********扣减库存完成******************");
    }
}
