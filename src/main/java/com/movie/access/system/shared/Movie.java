package com.movie.access.system.shared;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@XmlRootElement
public class Movie
{
    public Integer id;

    public Boolean adult;
    public String backdrop_path;
//    public List<Genre> genres;
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

//    public Collection belongs_to_collection;
    public Integer budget;
    public String homepage;
    public String imdb_id;
//    public List<BaseCompany> production_companies;
//    public List<Country> production_countries;
    public Integer revenue;
    public Integer runtime;
//    public List<SpokenLanguage> spoken_languages;
//    public Status status;
    public String tagline;

//    // Following are used with append_to_response
//    public AlternativeTitles alternative_titles;
//    public Changes changes;
//    public Keywords keywords;
//    public ListResultsPage lists;
//    public Images images;
//    public Translations translations;
//    public Credits credits;
//    public ReleaseDatesResults release_dates;
//    public MovieResultsPage similar;
//    public MovieResultsPage recommendations;
//    public ReviewResultsPage reviews;
//    public Videos videos;

//    public class Genre {
//
//        public Integer id;
//        public String name;
//
//    }

}
