package test.java.movie_search.movie;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "actor_info")
public class ActorInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private long idx;

    @Column(name = "actor_nm")
    private String actorNm;

    @Column(name = "actor_en_nm")
    private String actorEnNm;

    @Column(name = "cast_")
    private String cast;

    @Column(name = "movie_cd")
    private String movieCd;

    @Builder
    public ActorInfo(String actorNm, String actorEnNm, String cast, String movieCd) {
        this.actorNm = actorNm;
        this.actorEnNm=actorEnNm;
        this.cast=cast;
        this.movieCd = movieCd;
    }

}

