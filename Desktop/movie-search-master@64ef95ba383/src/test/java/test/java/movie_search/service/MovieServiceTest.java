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

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MovieServiceTest {

    ObjectMapper mapper = new ObjectMapper();
    @Autowired
    private MovieService movieService;

    @Test
    public void service_Test() throws IOException {
        movieService.getMovieData();
    }

    @Test
    public void getMovieList_영화_목록_가져오기_테스트() throws IOException {
        List<JsonNode> movieList = movieService.getMovieList();

        JsonNode firstPageCheck = movieList.get(0).path("movieCd");
        JsonNode lastPageCheck = movieList.get(28).path("movieCd");

        assertNotNull(movieList);
        assertNotNull(firstPageCheck);
        assertNotNull(lastPageCheck);

        System.out.println(movieList);

    }

    @Test
    public void getMovieCd_영화_코드_가져오기_테스트() throws IOException {
        List<String> movieCd = movieService.getMovieCd(movieService.getMovieList());

        assertNotNull(movieCd);

        System.out.println(movieCd);
        System.out.println(movieCd.size());
    }


    @Test
    public void getMovieDetail_영화_디테일_가져오기_테스트() throws IOException {
        List<String> movieCd = new ArrayList<>();
        movieCd.add("20060151");
        movieCd.add("20149314");
        movieCd.add("20184889");

        List<JsonNode> movieDetail = movieService.getMovieDetail(movieCd);

        assertThat((movieDetail.get(0).path("movieCd"))
                .textValue(), is("20060151"));
        assertThat((movieDetail.get(1).path("movieCd"))
                .textValue(), is("20149314"));
        assertThat((movieDetail.get(2).path("movieCd"))
                .textValue(), is("20184889"));

        System.out.println(movieDetail.get(0));
        System.out.println(movieDetail.get(1));
        System.out.println(movieDetail.get(2));

    }




}