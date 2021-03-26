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
@SuppressWarnings("OptionalUsedAsFieldOrParameterType")
public interface CommonMapper {

    default <T> ResponseEntity<T> toResponseEntity(Optional<T> optional, HttpStatus httpStatus) {
        return optional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(httpStatus)
                .build());
    }

    default <T> ResponseEntity<T> toResponseEntity(Optional<T> optional) {
        return toResponseEntity(optional, HttpStatus.BAD_REQUEST);
    }

    default ResponseEntity<?> toResponseEntity(boolean result, HttpStatus httpStatus) {
        return result ? ResponseEntity.ok().build() : ResponseEntity.status(httpStatus).build();
    }

    default ResponseEntity<?> toResponseEntity(boolean result) {
        return toResponseEntity(result, HttpStatus.BAD_REQUEST);
    }

    default <T> List<T> toList(T[] arr) {
        if (arr != null) {
            return Arrays.asList(arr);
        }
        return new ArrayList<>();
    }

    default <T> List<T> toList(Optional<T[]> arrOptional) {
        if (arrOptional.isPresent()) {
            return toList(arrOptional.get());
        }
        return new ArrayList<>();
    }
}
