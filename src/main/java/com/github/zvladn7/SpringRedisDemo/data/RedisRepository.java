package com.github.zvladn7.SpringRedisDemo.data;

import com.github.zvladn7.SpringRedisDemo.domain.Movie;

import java.util.Map;

public interface RedisRepository {

    Map<Object, Object> findAllMovies();
    void add(Movie movie);
    void delete(String id);
    Movie findMovie(String id);

}
