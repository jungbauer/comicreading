package com.comicreading.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.comicreading.domain.Comic;
import com.comicreading.repository.ComicRepository;

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

    public Comic findComicById(Integer id) throws Exception{
        if(id == null) throw new Exception("Id should not be null");
        Optional<Comic> optComic = comicRepository.findById(id);
        if(optComic.isPresent()) return optComic.get();
        else throw new Exception("Comic not found");
    }

    public void deleteComic(Integer id) throws Exception{
        if(id == null) throw new Exception("Id should not be null");
        Optional<Comic> optComic = comicRepository.findById(id);
        if(optComic.isPresent()) comicRepository.delete(optComic.get());
        else throw new Exception("Comic not found");
    }

    public Comic findComicByIdAndUserId(Integer id, Long userId) throws Exception{
        if(id == null) throw new Exception("Id should not be null");
        Optional<Comic> optComic = comicRepository.findByIdAndUserId(id, userId);
        if(optComic.isPresent()) return optComic.get();
        else throw new Exception("Comic not found");
    }

    public void addInitialTestComics() {
        comicRepository.save(new Comic("The Beginning After the End", "https://toonily.net/manga/the-beginning-after-the-end/", "171"));
		comicRepository.save(new Comic("Mercenary Enrollment", "https://toonily.net/manga/mercenary-enrollment/", "113"));
		comicRepository.save(new Comic("The Beginning After the End", "https://toonily.net/manga/reaper-of-the-drifting-moon/", "41"));
    }
    
}
