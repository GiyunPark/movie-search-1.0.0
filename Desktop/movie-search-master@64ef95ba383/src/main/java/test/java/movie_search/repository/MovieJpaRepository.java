package test.java.movie_search.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import test.java.movie_search.movie.MovieDetailInfo;

import java.util.List;

public interface MovieJpaRepository extends JpaRepository<MovieDetailInfo, Long> {
    MovieDetailInfo findByMovieNm(String name);

    @Query(value = "select * from Movie_Detail_Info where genre_alt LIKE %:genre%", nativeQuery = true)
    List<MovieDetailInfo> findAllLike(@Param("genre") String genre);
}
