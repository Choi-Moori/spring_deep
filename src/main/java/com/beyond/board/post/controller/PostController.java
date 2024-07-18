package com.beyond.board.post.controller;

import com.beyond.board.post.dto.PostCreateReqDto;
import com.beyond.board.post.dto.PostDetResDto;
import com.beyond.board.post.dto.PostListResDto;
import com.beyond.board.post.service.PostService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/post")
public class PostController {
    private final PostService postService;

    public PostController(PostService postService){
        this.postService=postService;
    }

    @PostMapping("/create")
    public String postCreate(@RequestBody PostCreateReqDto dto){
        postService.postCreate(dto);
        return "ok";
    }

    @GetMapping("/list")
    public List<PostListResDto> postList(){
        return postService.postList();
    }

    @GetMapping("/detail/{id}")
    public PostDetResDto postDetail(@PathVariable Long id){
        return postService.postDetail(id);
    }
}
