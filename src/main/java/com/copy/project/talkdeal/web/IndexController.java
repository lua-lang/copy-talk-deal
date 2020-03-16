package com.copy.project.talkdeal.web;

import com.copy.project.talkdeal.config.auth.LoginUser;
import com.copy.project.talkdeal.config.auth.dto.SessionUser;
import com.copy.project.talkdeal.service.posts.PostService;
import com.copy.project.talkdeal.web.dto.PostResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Controller
public class IndexController {
    private final PostService postService;
    private final HttpSession httpSession;

    @GetMapping("/")
    public String index(Model model, @LoginUser SessionUser user){
        model.addAttribute("post", postService.findAllDesc());

        if(user != null){
            model.addAttribute("userName", user.getName());
        }
        return "index";
    }

    @GetMapping("/posts/save")
    public String postSave(){
        return "post-save";
    }

    @GetMapping("/posts/update/{id}")
    public String postsUpdate(@PathVariable Long id, Model model){
        PostResponseDto dto = postService.findById(id);
        model.addAttribute("post", dto);

        return "post-update";
    }
}
