package com.moviesandchill.portalbackendservice.service.film;

import com.moviesandchill.portalbackendservice.dto.film.agelimit.AgeLimitDto;

import java.util.List;
import java.util.Optional;

public interface AgeLimitService {

    List<AgeLimitDto> getAllAgeLimit();

    void deleteAllAgeLimit();

    Optional<AgeLimitDto> getAgeLimitById(Long agelimit_id);

    Optional<AgeLimitDto> addAgeLimit(AgeLimitDto agelimit_dto);

    void deleteAgeLimitById(Long agelimit_id);
}
