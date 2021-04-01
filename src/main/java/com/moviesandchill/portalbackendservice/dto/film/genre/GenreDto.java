package com.moviesandchill.portalbackendservice.dto.film.genre;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GenreDto {

    private Long id_genre;

    private String genre_title;
}
