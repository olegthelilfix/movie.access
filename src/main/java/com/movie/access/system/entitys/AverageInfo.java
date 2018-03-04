package com.movie.access.system.entitys;

import lombok.Getter;
import lombok.Setter;

/**
 * Информация о средней оценке, и процессе загрузки данных.
 * А также время формирования данных.
 * @author Aleksandrov Oleg
 */
@Getter
public class AverageInfo
{
    @Setter
    private Double average = 0.0;

    @Setter
    private Double completionPercent = 0.0;

    private Long createDate = System.currentTimeMillis();

}
