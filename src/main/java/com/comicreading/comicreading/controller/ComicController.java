package com.comicreading.comicreading.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.comicreading.comicreading.domain.Comic;
import com.comicreading.comicreading.service.ComicService;

@Controller
public class ComicController {
    
    @Autowired
    private ComicService comicService;

    @GetMapping("/comics")
    public String comicList(Model model) {
        List<Comic> list = comicService.getAllComics();
        model.addAttribute("comics", list);
        return "comic/comicList";
    }

    @GetMapping("/addComic")
    public String comicForm(Model model) {
        model.addAttribute("comic", new Comic());
        return "comic/comicForm";
    }

    @PostMapping("/saveComic")
    public String saveComic(@ModelAttribute Comic comic, Model model) {
        Comic savedComic = comicService.saveComic(comic);
        model.addAttribute("comic", savedComic);
        return "comic/comicResult";
    }

    @GetMapping("/editComic")
    public String editComic(@RequestParam(name = "id") String comicId, Model model) {
        try {
            Comic editComic = comicService.findComicById(Integer.parseInt(comicId));
            model.addAttribute("comic", editComic);
            return "comic/comicForm";
        } catch (Exception e) {
            // TODO: handle exception
            return "error";
        }
        
    }

    @GetMapping("/incComic")
    public String incComic(@RequestParam(name = "id") String comicId, Model model) {
        try {
            Comic editComic = comicService.findComicById(Integer.parseInt(comicId));
            editComic.incrementChapter();
            comicService.saveComic(editComic);
            model.addAttribute("comics", comicService.getAllComics());
            return "comic/comicList";
        } catch (Exception e) {
            // TODO: handle exception
            return "error";
        }
        
    }

}
