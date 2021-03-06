package com.movie.access.system.entitys;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * Список краткой инфы по фильмам.
 * @author Aleksandrov Oleg
 */
@Getter
@Setter
@NoArgsConstructor
@XmlRootElement
public class MovieList
{
    private int page;
    private int total_results;
    private int total_pages;
    private List<ShortMovieInfo> results;
}
