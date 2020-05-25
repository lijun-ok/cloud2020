package com.atguigu.springcloud;

import com.atguigu.springcloud.service.StorageService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SeataStorageMain2002Test {

    @Autowired
    StorageService storageService;
    @Test
    public void test01(){
        storageService.decrease(1L,1);
        System.out.println("扣减库存完成");
    }
}
