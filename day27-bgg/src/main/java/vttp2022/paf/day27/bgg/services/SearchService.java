package vttp2022.paf.day27.bgg.services;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vttp2022.paf.day27.bgg.models.Comment;
import vttp2022.paf.day27.bgg.repositories.CommentRepository;
import vttp2022.paf.day27.bgg.repositories.LogRepository;

@Service
public class SearchService {

    @Autowired
    private CommentRepository commentRepo;

    @Autowired
    private LogRepository logRepo;

    public List<Comment> search(String q, Float score, Integer limit, Integer offset) {

        List<String> include = new LinkedList<>();
        List<String> exclude = new LinkedList<>();

        for (String w: q.split(" "))
            if (w.startsWith("-"))
                exclude.add(w);
            else 
                include.add(w);
    
        try {
            return commentRepo.search(include, exclude, limit)
                .stream()
                .filter(c -> c.getScore() >= score)
                .toList();
        } finally {
            logRepo.log(q, score);
        }
    }
}
