package com.comicreading.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.comicreading.domain.Comic;
import com.comicreading.service.ComicService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class IndexController {

    @Autowired
    private ComicService comicService;
    
    @GetMapping("/")
    public String homePage(Model model) {
        List<Comic> list = comicService.getAllComics();
        model.addAttribute("comics", list);
        log.debug("homepage hit");

        return "index";
    }
}
