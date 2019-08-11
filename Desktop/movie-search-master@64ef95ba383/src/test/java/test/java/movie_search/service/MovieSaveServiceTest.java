package test.java.movie_search.service;

import com.fasterxml.jackson.databind.JsonNode;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import test.java.movie_search.movie.ActorInfo;
import test.java.movie_search.movie.MovieDetailInfo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class MovieSaveServiceTest {

    @Autowired
    MovieSaveService movieSaveService;

    @Autowired
    MovieService movieService;

    @Autowired
    MovieParsingService movieParsingService;

    @Test
    public void saveMovieDetail_영화_데이터_저장_테스트() throws IOException {
        List<String> movieCd = new ArrayList<>();
        movieCd.add("20060151");
        movieCd.add("20149314");
        movieCd.add("20184889");

        List<JsonNode> movieDetail = movieService.getMovieDetail(movieCd);

        List<MovieDetailInfo> movieDetailInfoList = movieParsingService.parsingMovieDetail(movieDetail);

        movieSaveService.saveMovieDetail(movieDetailInfoList);

    }

    @Test
    public void saveActorInfo_배우_데이터_저장_테스트() throws IOException {
        List<String> movieCd = new ArrayList<>();
        movieCd.add("20060151");
        movieCd.add("20149314");
        movieCd.add("20184889");

        List<JsonNode> movieDetail = movieService.getMovieDetail(movieCd);

        List<ActorInfo> actorInfoList = movieParsingService.parsingActors(movieDetail);

        movieSaveService.saveActorInfo(actorInfoList);
    }

}