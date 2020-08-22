package com.github.zvladn7.SpringRedisDemo.controller;

import com.github.zvladn7.SpringRedisDemo.data.RedisRepository;
import com.github.zvladn7.SpringRedisDemo.domain.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
public class MovieController {

    private final RedisRepository redisRepo;

    @Autowired
    public MovieController(RedisRepository redisRepo) {
        this.redisRepo = redisRepo;
    }

    @GetMapping
    public String index() {
        return "movies";
    }

    @GetMapping("/keys")
    @ResponseBody
    public Map<Object, Object> keys() {
        return redisRepo.findAllMovies();
    }


    @PostMapping("/add")
    public ResponseEntity<String> add(
            @RequestParam String key,
            @RequestParam String value
    ) {
        Movie movie = new Movie(key, value);
        redisRepo.add(movie);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/values")
    @ResponseBody
    public Map<String, String> findAll() {
        Map<Object, Object> allMovies = redisRepo.findAllMovies();
        Map<String, String> movies;
        movies = allMovies.entrySet().stream().collect(
                HashMap::new,
                (map, t) -> map.put((String) t.getKey(), (String) t.getValue()),
                (m1, m2) -> m2.forEach(m1::put)
        );

        return movies;
    }

    @PostMapping("delete")
    public ResponseEntity<String> delete(@RequestParam String key) {
        redisRepo.delete(key);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
