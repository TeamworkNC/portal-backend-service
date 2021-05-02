package com.moviesandchill.portalbackendservice.mapper;

import com.moviesandchill.portalbackendservice.dto.film.review.FullReviewDto;
import com.moviesandchill.portalbackendservice.dto.film.review.ReviewDto;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;


@Mapper(
        componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS
)
public interface ReviewMapper {

    FullReviewDto mapToFullDto(ReviewDto dto);
}