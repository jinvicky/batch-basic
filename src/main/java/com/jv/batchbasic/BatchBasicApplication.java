package com.jv.batchbasic;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//@EnableBatchProcessing
// 어 이거 삭제했더니 결과가 달라?
// @EnableBatchProcessing을 추가할 경우 batch가 동작하지 않았다. 꼭 이 어노테이션을 추가하라는 말이 의심스럽다.
//https://stackoverflow.com/questions/74968433/spring-batch-job-to-run-on-startup
@SpringBootApplication
public class BatchBasicApplication {

    public static void main(String[] args) {
        SpringApplication.run(BatchBasicApplication.class, args);
    }

}
