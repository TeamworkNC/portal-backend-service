package com.moviesandchill.portalbackendservice.dto.film.film;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FilmPageDto {

    private Long id;

    private String name;

    private Float rating;

    private String logo;

    private String ageRestrictions;
}
