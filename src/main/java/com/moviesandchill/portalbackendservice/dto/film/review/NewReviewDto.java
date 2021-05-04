package com.moviesandchill.portalbackendservice.dto.film.review;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NewReviewDto {

    private Float ratingFilm;

    private String review;

    private LocalDate reviewDate;

    private Long idUser;
}
