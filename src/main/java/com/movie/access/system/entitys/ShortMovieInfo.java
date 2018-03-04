package com.movie.access.system.entitys;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;
import java.util.List;

/**
 * Краткая информация по фильму
 * @author Aleksandrov Oleg
 */
@Getter
@Setter
@NoArgsConstructor
@XmlRootElement
public class ShortMovieInfo
{
    private String poster_path;
    private boolean adult;
    private String overview;
    private Date release_date;
    private List<Integer> genre_ids;
    private int id;
    private String original_title;
    private String original_language;
    private String title;
    private String backdrop_path;
    private double popularity;
    private int vote_count;
    private boolean video;
    private double vote_average;
}
