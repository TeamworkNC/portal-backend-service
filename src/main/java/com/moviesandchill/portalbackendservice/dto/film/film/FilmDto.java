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

    private Long idFilm;

    private String filmTitle;

    private LocalDateTime duration;

    private LocalDate releaseDate;

    private String filmPoster;

    private String filmTrailer;

    private String filmVideo;

    private BigInteger filmBudget;

    private String description;

    private Float rating;

}
