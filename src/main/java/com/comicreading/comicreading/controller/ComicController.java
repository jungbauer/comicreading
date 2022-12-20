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

    // @GetMapping("/comicinit")
    // public String comicInit(Model model) {

    //     comicRepository.save(new Comic("The Beginning After the End", "https://toonily.net/manga/the-beginning-after-the-end/", "171"));
	// 	comicRepository.save(new Comic("Mercenary Enrollment", "https://toonily.net/manga/mercenary-enrollment/", "113"));
	// 	comicRepository.save(new Comic("The Beginning After the End", "https://toonily.net/manga/reaper-of-the-drifting-moon/", "41"));

    //     return "comic/comicInit";
    // }
}
