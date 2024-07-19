package com.beyond.board.author.controller;

import com.beyond.board.author.dto.AuthorCreateReqDto;
import com.beyond.board.author.dto.AuthorDetResDto;
import com.beyond.board.author.dto.AuthorListResDto;
import com.beyond.board.author.dto.AuthorUpdateReqDto;
import com.beyond.board.author.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/author")
public class AuthorController {
    private final AuthorService authorService;

    @Autowired
    public AuthorController(AuthorService authorService){
        this.authorService = authorService;
    }

    @GetMapping("/create")
    public String createView(){
        return "Author/author_register";
    }
    @PostMapping("/create")
    public String AuthorCreate(AuthorCreateReqDto dto){
        authorService.authorCreate(dto);
        return "redirect:/author/list";
    }

//    @GetMapping("/list")
//    public List<AuthorListResDto> AuthroList(){
//        return authorService.authorList();
//    }
    @GetMapping("/list")
    public String authorList(Model model){
        List<AuthorListResDto> authorList = authorService.authorList();
        model.addAttribute("authorList", authorList);
        return "/Author/author_list";
    }

//    @GetMapping("/detail/{id}")
//    public AuthorDetResDto AuthorDetail(@PathVariable Long id){
//
//        return authorService.authorDetail(id);
//    }
    @GetMapping("/detail/{id}")
    public String authorDetail(@PathVariable Long id, Model model){
        AuthorDetResDto authorDetResDto = authorService.authorDetail(id);
        model.addAttribute("author", authorDetResDto);
        return "/Author/author_detail";
    }

    @GetMapping("/delete/{id}")
    public String authorDelete(@PathVariable Long id){
        authorService.authorDelete(id);
        return "redirect:/author/list";
    }

    @PostMapping("/update/{id}")
    public String authorUpdate(@PathVariable Long id, AuthorUpdateReqDto dto){
        authorService.authorUpdate(id, dto);

        return "redirect:/author/detail/"+id;
    }
}
