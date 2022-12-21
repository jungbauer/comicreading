package com.comicreading.comicreading.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.comicreading.comicreading.domain.Comic;
import com.comicreading.comicreading.repository.ComicRepository;

@Service
public class ComicService {
    
    @Autowired
    private ComicRepository comicRepository;

    public List<Comic> getAllComics() {
        List<Comic> list = new ArrayList<>();
        for (Comic comic : comicRepository.findAll()) {
            list.add(comic);
        }

        return list;
    }

    public Comic saveComic(Comic comic) {
        //TODO there is a lot of error handling that is just not happening here.
        return comicRepository.save(comic);
    }

    public void addInitialTestComics() {
        comicRepository.save(new Comic("The Beginning After the End", "https://toonily.net/manga/the-beginning-after-the-end/", "171"));
		comicRepository.save(new Comic("Mercenary Enrollment", "https://toonily.net/manga/mercenary-enrollment/", "113"));
		comicRepository.save(new Comic("The Beginning After the End", "https://toonily.net/manga/reaper-of-the-drifting-moon/", "41"));
    }
    
}
