package com.comicreading.comicreading.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.comicreading.comicreading.domain.Comic;
import com.comicreading.comicreading.repository.ComicRepository;

@Controller
public class ComicController {
    
    @Autowired
    private ComicRepository comicRepository;

    @GetMapping("/comics")
    public String comicList(Model model) {
        List<Comic> list = new ArrayList<>();
        for (Comic comic : comicRepository.findAll()) {
            list.add(comic);
        }
        model.addAttribute("comics", list);
        return "comic/comicList";
    }
}
