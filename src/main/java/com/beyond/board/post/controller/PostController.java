package com.beyond.board.post.controller;

import com.beyond.board.post.dto.PostCreateReqDto;
import com.beyond.board.post.dto.PostDetResDto;
import com.beyond.board.post.dto.PostListResDto;
import com.beyond.board.post.dto.PostUpdateReqDto;
import com.beyond.board.post.service.PostService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
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
    public String postCreate(@ModelAttribute PostCreateReqDto dto){
        postService.postCreate(dto);
        return "redirect:/post/list";
    }


    @GetMapping("/list")
    public String postList(Model model,
                           @PageableDefault(size = 5,
                                   sort = "createdTime",
                                   direction = Sort.Direction.DESC)
                           Pageable pageable){
        model.addAttribute("postList", postService.postList(pageable));
        return "Post/post_list";
    }

    @GetMapping("/list/page")
    @ResponseBody
//    Pageable요청 방법 : localhost:8080/post/list?size=10&page=0
    public Page<PostListResDto> postListPage(@PageableDefault(size = 10,
                                                              sort = "createdTime",
                                                              direction = Sort.Direction.DESC)
                                                 Pageable pageable){
        return postService.postListPage(pageable);
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
