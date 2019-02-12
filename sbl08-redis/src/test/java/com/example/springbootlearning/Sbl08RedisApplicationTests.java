package com.example.springbootlearning;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.springbootlearning.dao.RedisDao;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Sbl08RedisApplicationTests {

    public static Logger logger = LoggerFactory.getLogger(Sbl08RedisApplicationTests.class);

    @Test
    public void contextLoads() {
    }

    @Autowired
    RedisDao redisDao;

    @Test
    public void testRedis() {
        this.redisDao.setKey("zhangsan", "zhangsan@mail.com");
        this.redisDao.setKey("wangwu", "wangwu@mail.com");
        logger.info(this.redisDao.getValue("zhangsan"));
        logger.info(this.redisDao.getValue("wangwu"));
    }
}
