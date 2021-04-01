package com.moviesandchill.portalbackendservice.dto.film.film;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FilmDto {

    private Long id_film;

    private String film_title;

    private LocalDateTime duration;

    private LocalDate release_date;

    private String film_poster;

    private String film_trailer;

    private String film_video;

    private BigInteger film_budget;

    private String description;
}
