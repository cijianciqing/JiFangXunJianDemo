package com.example.demo.service;

import cn.hutool.core.date.DateUtil;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class MyServiceTest {

    /*
    * second , minute, hour, day of month, month and day of week.
    * {@code "0 * * * * MON-FRI"} means once per minute on weekdays
    * */
    //(cron = "30 0 12,18 1-31 * *" )每天12点和18点的 第0分 第一秒开始
    @Scheduled(cron = "1 0 0 1-31 * *" )
    public void testScheduler(){
        System.out.println(DateUtil.date(System.currentTimeMillis())+" hello scheduler...");
    }
}
