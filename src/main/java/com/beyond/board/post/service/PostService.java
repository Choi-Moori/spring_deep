package com.beyond.board.post.service;

import com.beyond.board.author.domain.Author;
import com.beyond.board.author.service.AuthorService;
import com.beyond.board.post.domain.Post;
import com.beyond.board.post.dto.PostCreateReqDto;
import com.beyond.board.post.dto.PostDetResDto;
import com.beyond.board.post.dto.PostListResDto;
import com.beyond.board.post.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class PostService {
    private final PostRepository postRepository;
    private final AuthorService authorService;

    // authorService 또는 repository를 autowired 할지는 상황에 따라 다르나,
    // service 레벨에서 코드가 고도화 되어 있고 코드의 중복이 심할 경우, service 레이어를 autowired
    // 그러나, 순환참조가 발생 할 경우에는 repository를 autowired
    @Autowired
    public PostService(PostRepository postRepository, AuthorService authorService){
        this.postRepository = postRepository;
        this.authorService = authorService;
    }

//    authorservice에서 author객체를 찾아 post의 toEntity에 넘기고,
//    jpa가 author객체에서 author_id를 찾아 db에는 author_id가 들어감.
    public void postCreate(PostCreateReqDto dto){
//        Long author_id = dto.getAuthor_id();
//        authorRepository.findById(author_id).orElseThrow(()-> new EntityNotFoundException("없는 회원"));
        Author author = authorService.authorFindByEmail(dto.getAuthor_email());

        Post post = dto.toEntity(author);
        postRepository.save(post);
    }

    public List<PostListResDto> postList(){
        List<Post> postList = postRepository.findAll();
        List<PostListResDto> postResDtoList = new ArrayList<>();

        for(Post post : postList){
            postResDtoList.add(post.listFromEntity());
        }
        return postResDtoList;
    }

    public PostDetResDto postDetail(Long id){
        Post post = postRepository
                .findById(id)
                .orElseThrow(()->new EntityNotFoundException("없는 글 입니다"));

        return post.detFromEntity();
    }
}
