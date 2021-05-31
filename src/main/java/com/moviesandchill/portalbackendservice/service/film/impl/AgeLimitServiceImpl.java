package com.moviesandchill.portalbackendservice.service.film.impl;

import com.moviesandchill.portalbackendservice.dto.film.agelimit.AgeLimitDto;
import com.moviesandchill.portalbackendservice.mapper.CommonMapper;
import com.moviesandchill.portalbackendservice.service.film.AgeLimitService;
import com.moviesandchill.portalbackendservice.utils.RestTemplateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AgeLimitServiceImpl implements AgeLimitService {

    private final CommonMapper commonMapper;
    private String filmServiceUrl;

    public AgeLimitServiceImpl(CommonMapper commonMapper) {
        this.commonMapper = commonMapper;
    }

    @Override
    public List<AgeLimitDto> getAllAgeLimit() {
        String url = filmServiceUrl + "/ageLimits";
        Optional<AgeLimitDto[]> listAgeLimitDtoOptional = RestTemplateUtils.get(url, AgeLimitDto[].class);
        return commonMapper.toList(listAgeLimitDtoOptional);
    }

    @Override
    public void deleteAllAgeLimit() {
        String url = filmServiceUrl + "/ageLimits";
        RestTemplateUtils.delete(url);
    }

    @Override
    public Optional<AgeLimitDto> getAgeLimitById(Long agelimit_id) {
        String url = filmServiceUrl + "/ageLimits/" + agelimit_id;
        return RestTemplateUtils.get(url, null, AgeLimitDto.class);
    }

    @Override
    public Optional<AgeLimitDto> addAgeLimit(AgeLimitDto agelimit_dto) {
        String url = filmServiceUrl + "/ageLimits";
        return RestTemplateUtils.post(url, agelimit_dto, AgeLimitDto.class);
    }

    @Override
    public void deleteAgeLimitById(Long agelimit_id) {
        String url = filmServiceUrl + "/ageLimits/" + agelimit_id;
        RestTemplateUtils.delete(url);
    }

    @Autowired
    public void setFilmServiceUrl(@Value("${endpoints.film-service-url}") String filmServiceUrl) {
        this.filmServiceUrl = filmServiceUrl;
    }
}
