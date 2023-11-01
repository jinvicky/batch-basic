package com.jv.batchbasic.config;


import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.*;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@AllArgsConstructor
@EnableScheduling
public class BatchScheduler {

    private final JobLauncher jobLauncher;
    private final Job myJob;

    @Scheduled(cron= "0 * * * * *")
    public void runJob() {
       JobParametersBuilder jobParametersBuilder = new JobParametersBuilder();
        jobParametersBuilder.addLong("currentTime", System.currentTimeMillis());

        try {
            jobLauncher.run(myJob, jobParametersBuilder.toJobParameters());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
