package test.java.movie_search.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import test.java.movie_search.movie.ActorInfo;

public interface ActorJpaRepository extends JpaRepository<ActorInfo, Long> {

}
