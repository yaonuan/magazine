package com.magazine.schedule;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

/**
 * @Author : yaonuan
 * @Email : 806039077@qq.com
 * @Date : 2019-05-05
 */
@Service
public class ClusterLockJob {

//    @Scheduled(cron = "0/5 * * * * *")
    public void executeJob(){
        System.out.println("enter job");
    }

}
