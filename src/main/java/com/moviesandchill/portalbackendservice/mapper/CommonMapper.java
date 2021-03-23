package com.moviesandchill.portalbackendservice.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Mapper(
        componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS
)
public interface CommonMapper {
    @SuppressWarnings("OptionalUsedAsFieldOrParameterType")
    default <T> ResponseEntity<T> toResponseEntity(Optional<T> optional) {
        return toResponseEntity(optional, HttpStatus.BAD_REQUEST);
    }

    @SuppressWarnings("OptionalUsedAsFieldOrParameterType")
    default <T> ResponseEntity<T> toResponseEntity(Optional<T> optional, HttpStatus httpStatus) {
        return optional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(httpStatus)
                .build());
    }

    default <T> List<T> toList(T[] arr) {
        if (arr != null) {
            return Arrays.asList(arr);
        }
        return new ArrayList<>();
    }
}
