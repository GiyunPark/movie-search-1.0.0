package test.java.movie_search.service;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;
import test.java.movie_search.movie.ActorInfo;
import test.java.movie_search.movie.MovieDetailInfo;
import test.java.movie_search.repository.ActorJpaRepository;
import test.java.movie_search.repository.MovieJpaRepository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;


@Service
public class MovieService {

    static final int MAX_CUR_PAGE = 30;
    static final int MAX_ITEM_PER_PAGE = 100;
    static final String KEY = "4669ad52bda6c653b9a220e6229ba80b";
    static final int OPEN_START_DT = 2018;
    static final int OPEN_END_DT = 2019;

    ObjectMapper mapper = new ObjectMapper();
    RestTemplate restTemplate = new RestTemplate();
    MovieJpaRepository movieJpaRepository;
    ActorJpaRepository actorJpaRepository;
    MovieParsingService movieParsingService;

    public MovieService(MovieJpaRepository movieJpaRepository, ActorJpaRepository actorJpaRepository
            ,MovieParsingService movieParsingService) {
        this.movieJpaRepository = movieJpaRepository;
        this.actorJpaRepository = actorJpaRepository;
        this.movieParsingService = movieParsingService;
    }

    public void getMovieData() throws IOException {
        List<JsonNode> movieDetail = getMovieDetail(getMovieCd(getMovieList()));

    }

    public List<JsonNode> getMovieList() throws IOException {

        List<JsonNode> movieList = new ArrayList<>();
        for (int curPage = 1; curPage < MAX_CUR_PAGE; curPage++) {
            String movieUrl = "http://www.kobis.or.kr/kobisopenapi/webservice/rest/movie/searchMovieList.json";
            UriComponents builder = UriComponentsBuilder.fromHttpUrl(movieUrl)
                    .queryParam("key", KEY)
                    .queryParam("curPage", curPage)
                    .queryParam("itemPerPage", MAX_ITEM_PER_PAGE)
                    .queryParam("openStartDt", OPEN_START_DT)
                    .queryParam("openEndDt", OPEN_END_DT)
                    .build(false);
            movieList.add(mapper.readTree(restTemplate.getForObject(builder.toUriString(), String.class))
                    .path("movieListResult").path("movieList"));
        }
        return movieList;
    }

    public List<String> getMovieCd(List<JsonNode> movieList) {
        List<String> movieCd = new ArrayList<>();
        for (JsonNode movieListNode : movieList) {
                movieCd.addAll(movieParsingService.parsingMovieCd(movieListNode));
            }
        return movieCd;
    }


    public List<JsonNode> getMovieDetail(List<String> movieCdList) throws IOException {
        String movieDetailUrl = "http://www.kobis.or.kr/kobisopenapi/webservice/rest/movie/searchMovieInfo.json";
        List<JsonNode> movieDetail = new ArrayList<>();
        for (String movieCd : movieCdList) {
            UriComponents builder = UriComponentsBuilder.fromHttpUrl(movieDetailUrl)
                    .queryParam("key", KEY)
                    .queryParam("movieCd", movieCd)
                    .build(false);
            movieDetail.add(mapper.readTree(restTemplate.getForObject(builder.toUriString(), String.class))
                    .path("movieInfoResult").path("movieInfo"));
        }
        return movieDetail;
    }

}

