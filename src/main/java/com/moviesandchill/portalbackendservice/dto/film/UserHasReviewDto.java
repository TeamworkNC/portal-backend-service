package com.moviesandchill.portalbackendservice.dto.film;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserHasReviewDto {

    private Long id_user_has_review;

    private Long id_user;

    private Boolean user_score;
}
