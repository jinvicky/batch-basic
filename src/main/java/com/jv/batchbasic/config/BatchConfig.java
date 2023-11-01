package com.jv.batchbasic.config;


import com.jv.batchbasic.domain.Post;
import com.jv.batchbasic.repository.PostRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.json.JacksonJsonObjectReader;
import org.springframework.batch.item.json.builder.JsonItemReaderBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.UrlResource;
import org.springframework.transaction.PlatformTransactionManager;

import java.net.MalformedURLException;
import java.net.URL;

@Configuration
@AllArgsConstructor
@Slf4j
public class BatchConfig {

    private final PostRepository postRepository;

    @Bean
    public Job myJob(JobRepository jobRepository, PlatformTransactionManager transactionManager) throws MalformedURLException {
        return new JobBuilder("myJob", jobRepository)
                .start(myStep( jobRepository, transactionManager))
                .build();
    }

    @Bean
    public Step myStep(JobRepository jobRepository, PlatformTransactionManager transactionManager) throws MalformedURLException {
        return new StepBuilder("myStep", jobRepository)
                .<Post, Post>chunk(10, transactionManager)
                .reader(customItemReader())
                .writer(customItemWriter())
                .build();
    }

    @Bean
    public ItemReader<Post> customItemReader() throws MalformedURLException {
        String url = "https://jsonplaceholder.typicode.com/posts";
        URL resource = new URL(url);
        return new JsonItemReaderBuilder<Post>()
                .name("jsonReader")
                .resource(new UrlResource(resource))
                .jsonObjectReader(new JacksonJsonObjectReader<>(Post.class))
                .build();
    }

    @Bean
    public ItemWriter<Post> customItemWriter() {
        return items -> {
            for (Post post : items) {
                postRepository.save(post);
//                return; // return;을 추가하면 1분에 10개씩 가지고 온다.
            }
        };
    }
}