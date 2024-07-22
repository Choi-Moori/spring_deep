package com.beyond.board.post.repository;

import com.beyond.board.post.domain.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

//    Page<Post> : List<Post> + 해당요소의 Page 정보
//    Pageable : PageNumber(몇번페이지 - default 0번 페이지)
//               Size(페이지마다 몇 페이지씩 - default 20개)
//               정렬조건
//    페이징 처리하는 이유 : 서버의 부하를 줄이기 위해
    Page<Post> findAll(Pageable pageable);

//    jpql을 적용하여 네이밍룰을 통한 방식이 아닌 메서드 생성.
//    select p.*, a.* from post p left join author a on p.author_id = a.id;
    @Query("select p from Post p left join fetch p.author")
    List<Post> findAllFetch();

//    select p.* from post p left join author a on p.author_id=a.id;
    @Query("select p from Post p left join p.author")
    List<Post> findAllLeftJoin();
}
