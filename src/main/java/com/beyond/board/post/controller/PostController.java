package com.beyond.board.post.controller;

import com.beyond.board.post.dto.PostCreateReqDto;
import com.beyond.board.post.dto.PostDetResDto;
import com.beyond.board.post.dto.PostListResDto;
import com.beyond.board.post.dto.PostUpdateReqDto;
import com.beyond.board.post.service.PostService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/post")
public class PostController {
    private final PostService postService;

    public PostController(PostService postService){
        this.postService=postService;
    }

    @GetMapping("/create")
    public String postCreateView(){
        return "/Post/post_register";
    }
    @PostMapping("/create")
    public String postCreate(PostCreateReqDto dto){
        postService.postCreate(dto);
        return "redirect:/post/list";
    }


    @GetMapping("/list")
    public String postList(Model model){
        model.addAttribute("postList", postService.postList());
        return "Post/post_list";
    }

    @GetMapping("/detail/{id}")
    public String postDetail(@PathVariable Long id, Model model){
        System.out.println(postService.postDetail(id));
        model.addAttribute("post", postService.postDetail(id));
        return "Post/post_detail";
    }

    @GetMapping("/delete/{id}")
    public String postDelete(@PathVariable Long id){
        postService.postDelete(id);
        return "redirect:/post/list";
    }

    @PostMapping("/update/{id}")
    public String postUpdate(@PathVariable Long id, PostUpdateReqDto dto){
        postService.postUpdate(id, dto);

        return "redirect:/post/detail/"+id;
    }
}
