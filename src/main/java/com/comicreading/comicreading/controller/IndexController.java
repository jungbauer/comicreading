package com.comicreading.comicreading.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.comicreading.comicreading.domain.Comic;
import com.comicreading.comicreading.service.ComicService;

@Controller
public class IndexController {

    @Autowired
    private ComicService comicService;
    
    @GetMapping("/")
    public String homePage(Model model) {
        List<Comic> list = comicService.getAllComics();
        model.addAttribute("comics", list);
        return "comic/comicListMain";
    }
}
