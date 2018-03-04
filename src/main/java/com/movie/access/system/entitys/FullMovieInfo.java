package com.movie.access.system.entitys;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;
import java.util.List;

/**
 * Полная информация по фильму
 *
 * @author Aleksandrov Oleg
 */
@Getter
@Setter
@NoArgsConstructor
@XmlRootElement
public class FullMovieInfo
{
    public Integer id;

    public Boolean adult;
    public String backdrop_path;
    public List<Integer> genre_ids;
    public String original_title;
    public String original_language;
    public String overview;
    public Double popularity;
    public String poster_path;
    public Date release_date;
    public String title;
    public Double vote_average;
    public Integer vote_count;

    public Integer budget;
    public String homepage;
    public String imdb_id;
    public Integer revenue;
    public Integer runtime;
    public String tagline;
}
