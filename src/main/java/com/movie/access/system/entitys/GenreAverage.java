package com.movie.access.system.entitys;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * TODO TEXT
 * @author Aleksandrov Oleg
 */
@Getter
@Setter
@NoArgsConstructor
@XmlRootElement
public class GenreAverage
{
    private double vote_average;
}
