package com.beyond.board.post.service;

import com.beyond.board.post.domain.Post;
import com.beyond.board.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;

/**

 batch를 돌리기 위한 스케줄러 정의*/
@Configuration
//@RequiredArgsConstructor
public class PostJobConfiguration {

    @Autowired
    private JobBuilderFactory jobBuilderFactory;
    @Autowired
    private StepBuilderFactory stepBuilderFactory;
    @Autowired
    private PostRepository postRepository;

    public Job excuteJob(){
        return jobBuilderFactory.get("excuteJob")
                .start(firstStep())
                .next(secondStep())
                .build();
    }

    @Bean
    public Step firstStep(){
        return stepBuilderFactory.get("firstStep")
                .tasklet((stepContribution, chunkContext) -> {
                    System.out.println("=== 예약 글쓰기 batch task1 시작===");
                    Page<Post> posts = postRepository.findByAppointment(Pageable.unpaged(), "Y");
                    for(Post p : posts){
                        // 예약 시간이 나우보다 이전이면
                        if(p.getAppointmentTime().isBefore(LocalDateTime.now())){
                            // N 처리
                            p.updateAppointment("N");
                        }
                    }
                    System.out.println("=== 예약 글쓰기 batch task1 종료");
                    return RepeatStatus.FINISHED;
                }).build();
    }
    @Bean
    public Step secondStep(){
        return stepBuilderFactory.get("secondStep")
                .tasklet((stepContribution, chunkContext) -> {
                    System.out.println("=== 예약 글쓰기 batch task2 시작===");
                    System.out.println("=== 예약 글쓰기 batch task2 종료");
                    return RepeatStatus.FINISHED;
                }).build();
    }
}
