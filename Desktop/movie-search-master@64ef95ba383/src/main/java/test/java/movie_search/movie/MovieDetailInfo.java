package test.java.movie_search.movie;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "movie_detail_info")
public class MovieDetailInfo {

    @Id
    @Column(name = "movie_cd")
    private String movieCd;

    @Column(name = "movie_nm")
    private String movieNm;

    @Column(name = "movie_nm_en")
    private String movieNmEn;

    @Column(name = "open_dt")
    private String openDt;

    @Column(name = "nation_alt")
    private String nationAlt;

    @Column(name = "genre_alt")
    private String genreAlt;

    @Column(name = "prdt_stat_nm")
    private String prdtStatNm;

    @JoinColumn(name = "movie_cd")
    @OneToMany(fetch = FetchType.LAZY)
    private List<ActorInfo> actors = new ArrayList<>();

    @Column(name = "director_nm")
    private String directorNm;

    @Column(name = "watch_grade_Nm")
    private String watchGradeNm;


    @Builder
    public MovieDetailInfo(String movieNm, String movieNmEn, String openDt, String nationAlt, String genreAlt, String prdtStatNm
            , String directorNm, String watchGradeNm, String movieCd) {
        this.movieNm = movieNm;
        this.movieNmEn = movieNmEn;
        this.openDt = openDt;
        this.nationAlt = nationAlt;
        this.genreAlt = genreAlt;
        this.prdtStatNm = prdtStatNm;
        this.directorNm = directorNm;
        this.watchGradeNm = watchGradeNm;
        this.movieCd = movieCd;
    }
}


