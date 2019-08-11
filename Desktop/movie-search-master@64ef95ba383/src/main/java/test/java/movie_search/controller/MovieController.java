package test.java.movie_search.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import test.java.movie_search.movie.MovieDetailInfo;
import test.java.movie_search.repository.MovieJpaRepository;

import java.util.List;

@RestController
public class MovieController {


    @Autowired
    MovieJpaRepository movieJpaRepository;

    @RequestMapping("/movie")
    public ResponseEntity ReadMovie(@RequestParam("name") String name) {
        MovieDetailInfo movieDetailInfo = movieJpaRepository.findByMovieNm(name);
        return new ResponseEntity(movieDetailInfo, HttpStatus.OK);
    }

    @RequestMapping("/genre")
    public ResponseEntity ReadGenre(@RequestParam("genre") String genre){
        List<MovieDetailInfo> movieNameDto = movieJpaRepository.findAllLike(genre);
        return new ResponseEntity(movieNameDto, HttpStatus.OK);
    }
}
