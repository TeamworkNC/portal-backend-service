package com.moviesandchill.portalbackendservice.dto.film;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReviewDto {

    private Long id_review;

    private Float rating_film;

    private String review;

    private LocalDate review_date;

    private Long id_user;
}
