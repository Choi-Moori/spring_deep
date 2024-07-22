package com.beyond.board.post.service;

import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

//batch를 돌리기위한 스케줄려 정의
@Component
public class PostBatchSchedular {

    @Autowired
    private JobLauncher jobLauncher;
    @Autowired
    PostJobConfiguration postJobConfiguration;


    @Scheduled(cron = "0 0/1 * * * *")
    public void batchSchedule(){
        Map<String, JobParameter> configMap = new HashMap<>();
        configMap.put("time", new JobParameter(System.currentTimeMillis()));
        JobParameters jobParameters = new JobParameters(configMap);
        try {
            this.jobLauncher.run(postJobConfiguration.excuteJob(), jobParameters);
        }catch ( Exception e){
            e.printStackTrace();
        }
    }
}
