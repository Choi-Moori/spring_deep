//package com.beyond.board.post.service;
//
//import com.beyond.board.post.domain.Post;
//import com.beyond.board.post.repository.PostRepository;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.Pageable;
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.stereotype.Component;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.time.LocalDateTime;
//
//@Component
//public class PostSchedular {
//    private final PostRepository postRepository;
//
//    public PostSchedular(PostRepository postRepository){
//        this.postRepository = postRepository;
//    }
//
////    아래 스케쥴의 cro부는 각 자리마다 "초 분 시간 일 월 요일"을 의미
////    예를 들어) * * * * * * : 매일 매분 매초마다 시간
////    예를 들어) 0 0 * * * * : 매일 0분 0초마다
////    예를 들어) 0 0 11 * * * : 매일 11시에
////    예를 들어) 0 0/1 * * * * : 매일 매시 1분마다
////    예를 들어) 0 1 * * * * : 매일 매시 1분에
//    @Scheduled(cron = "0 0/1 * * * *")
//    @Transactional
//    public void postSchedule(){
//        System.out.println("== 예약글쓰기 스케쥴러 시작===");
//        Page<Post> posts = postRepository.findByAppointment(Pageable.unpaged(), "Y");
//        for(Post p : posts){
//            // 예약 시간이 나우보다 이전이면
//            if(p.getAppointmentTime().isBefore(LocalDateTime.now())){
//                // N 처리
//                p.updateAppointment("N");
//            }
//        }
//    }
//}
