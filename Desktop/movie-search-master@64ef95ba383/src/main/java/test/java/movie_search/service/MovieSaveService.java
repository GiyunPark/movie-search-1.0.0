package test.java.movie_search.service;

import test.java.movie_search.movie.ActorInfo;
import test.java.movie_search.movie.MovieDetailInfo;
import test.java.movie_search.repository.ActorJpaRepository;
import test.java.movie_search.repository.MovieJpaRepository;

import java.util.List;

public class MovieSaveService {

    MovieJpaRepository movieJpaRepository;
    ActorJpaRepository actorJpaRepository;

    public MovieSaveService(MovieJpaRepository movieJpaRepository, ActorJpaRepository actorJpaRepository) {
        this.movieJpaRepository = movieJpaRepository;
        this.actorJpaRepository = actorJpaRepository;
    }


    public void saveMovieDetail(List<MovieDetailInfo> movieDetailInfoList) {
        for (MovieDetailInfo movieDetailInfo : movieDetailInfoList) {
            movieJpaRepository.save(movieDetailInfo);
        }
    }

    public void saveActorInfo(List<ActorInfo> actorInfoList) {
        for (ActorInfo actorInfo : actorInfoList) {
            actorJpaRepository.save(actorInfo);
        }
    }
}
