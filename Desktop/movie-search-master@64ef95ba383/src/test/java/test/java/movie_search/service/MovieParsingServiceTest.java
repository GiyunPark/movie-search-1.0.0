package test.java.movie_search.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
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
public class MovieParsingServiceTest {

    @Autowired
    MovieService movieService;

    @Autowired
    MovieParsingService movieParsingService;

    ObjectMapper mapper = new ObjectMapper();

    @Test
    public void parsingMovieDetail_영화_디테일_파싱_테스트() throws IOException {
        List<String> movieCd = new ArrayList<>();
        movieCd.add("20060151");
        movieCd.add("20149314");
        movieCd.add("20184889");

        List<JsonNode> movieDetail = movieService.getMovieDetail(movieCd);

        List<MovieDetailInfo> movieDetailInfoList = movieParsingService.parsingMovieDetail(movieDetail);
        System.out.println(mapper.writeValueAsString(movieDetailInfoList.get(0)));
        System.out.println(mapper.writeValueAsString(movieDetailInfoList.get(1)));
        System.out.println(mapper.writeValueAsString(movieDetailInfoList.get(2)));

    }

    @Test
    public void parsingActorInfo_배우_정보_파싱_테스트() throws IOException {
        List<String> movieCd = new ArrayList<>();
        movieCd.add("20060151");
        movieCd.add("20149314");
        movieCd.add("20184889");

        List<JsonNode> movieDetail = movieService.getMovieDetail(movieCd);

        List<ActorInfo> actorInfoList = movieParsingService.parsingActors(movieDetail);
        System.out.println(mapper.writeValueAsString(actorInfoList.get(0)));
        System.out.println(mapper.writeValueAsString(actorInfoList.get(1)));
        System.out.println(mapper.writeValueAsString(actorInfoList.get(2)));
    }
}