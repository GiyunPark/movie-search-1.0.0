package test.java.movie_search.service;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.stereotype.Service;
import test.java.movie_search.movie.ActorInfo;
import test.java.movie_search.movie.MovieDetailInfo;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

@Service
public class MovieParsingService {

    static final String COMMA = ",";

    public List<MovieDetailInfo> parsingMovieDetail(List<JsonNode> movieDetail) {
        List<MovieDetailInfo> movieDetailInfoList = new ArrayList<>();
        for (JsonNode movieInfo : movieDetail) {
            JsonNode genres = movieInfo.path("genres");
            MovieDetailInfo movieDetailInfo = MovieDetailInfo.builder()
                    .movieNm(movieInfo.path("movieNm").textValue())
                    .movieNmEn(movieInfo.path("movieNmEn").textValue())
                    .openDt(movieInfo.path("openDt").textValue())
                    .nationAlt(movieInfo.path("nations").path(0).path("nationNm").textValue())
                    .genreAlt(parsingGenres(genres))
                    .prdtStatNm(movieInfo.path("prdtStatNm").textValue())
                    .directorNm(movieInfo.path("directors").path(0).path("peopleNm").textValue())
                    .watchGradeNm(movieInfo.path("audits").path(0).path("watchGradeNm").textValue())
                    .movieCd(movieInfo.path("movieCd").textValue())
                    .build();
            movieDetailInfoList.add(movieDetailInfo);
        }
        return movieDetailInfoList;
    }

    public String parsingGenres(JsonNode genres) {
        StringJoiner joiner = new StringJoiner(COMMA);
        String joinedGenre = null;
        for (JsonNode node : genres) {
            String genre = node.path("genreNm").textValue();
            joiner.add(genre);
            joinedGenre = joiner.toString();
        }
        return joinedGenre;
    }

    public List<ActorInfo> parsingActors(List<JsonNode> movieDetail) {
        List<ActorInfo> actorInfoList = new ArrayList<>();

        for (JsonNode movieDetailNode : movieDetail) {
            JsonNode actorsInfo = movieDetailNode.path("actors");
            actorInfoList = parsingActorInfo(actorsInfo, movieDetailNode);
        }

        return actorInfoList;
    }

    public List<ActorInfo> parsingActorInfo(JsonNode actorsInfo, JsonNode movieDetailNode) {
        List<ActorInfo> actorInfoList = new ArrayList<>();
        for (JsonNode actorsInfoNode : actorsInfo) {
            ActorInfo actorInfo = ActorInfo.builder()
                    .actorNm(actorsInfoNode.path("peopleNm").textValue())
                    .actorEnNm(actorsInfoNode.path("peopleNmEn").textValue())
                    .cast(actorsInfoNode.path("cast").textValue())
                    .movieCd(movieDetailNode.path("movieCd").textValue())
                    .build();
            actorInfoList.add(actorInfo);
        }
        return actorInfoList;
    }

    public List<String> parsingMovieCd(JsonNode movieListNode) {
        List<String> movieCd = new ArrayList<>();
        for (JsonNode node : movieListNode) {
            movieCd.add(node.path("movieCd").textValue());
        }return movieCd;
    }

}
