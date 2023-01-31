package com.comicreading.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.comicreading.domain.Comic;
import com.comicreading.service.ComicService;

@Controller
public class ComicController {
    
    @Autowired
    private ComicService comicService;

    @GetMapping("/comics")
    public String comicListDetailed(Model model) {
        List<Comic> list = comicService.getAllComics();
        model.addAttribute("comics", list);
        return "comic/comicListDetailed";
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
    public ModelAndView incComic(@RequestParam(name = "id") String comicId, Model model, 
    @RequestHeader(value = HttpHeaders.REFERER, required = false) String referer) {
        try {
            Comic editComic = comicService.findComicById(Integer.parseInt(comicId));
            editComic.incrementChapter();
            comicService.saveComic(editComic);
            if(referer.substring(referer.length()-6, referer.length()).equals("comics"))
                return new ModelAndView("redirect:comics");
            else return new ModelAndView("redirect:/");
        } catch (Exception e) {
            // TODO: handle exception
            return new ModelAndView("error");
        }
        
    }

    @GetMapping("/deleteComic")
    public ModelAndView deleteComic(@RequestParam(name = "id") String comicId, Model model) {
        //TODO this really needs a confirmation modal etc
        try {
            comicService.deleteComic(Integer.parseInt(comicId));
            return new ModelAndView("redirect:comics");
        } catch (Exception e) {
            // TODO: handle exception
            return new ModelAndView("error");
        }
        
    }
}
