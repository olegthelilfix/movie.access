package com.movie.access.system.entitys;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * Список средних оценок для каждого фильма, с доп информации о странице,
 * с которой были загруженны данные и о том сколько страниц еще осталось.
 *
 * @author Aleksandrov Oleg
 */
@Getter
@Setter
@NoArgsConstructor
@XmlRootElement
public class GenreAverageList
{
    private int page;
    private int total_pages;
    private List<GenreAverage> results;
}
