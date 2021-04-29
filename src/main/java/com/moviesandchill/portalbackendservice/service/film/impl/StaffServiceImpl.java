package com.moviesandchill.portalbackendservice.service.film.impl;

import com.moviesandchill.portalbackendservice.dto.film.agelimit.AgeLimitDto;
import com.moviesandchill.portalbackendservice.dto.film.staff.StaffDto;
import com.moviesandchill.portalbackendservice.mapper.CommonMapper;
import com.moviesandchill.portalbackendservice.service.film.StaffService;
import com.moviesandchill.portalbackendservice.utils.RestTemplateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StaffServiceImpl implements StaffService {

    private String filmServiceUrl;

    private final CommonMapper commonMapper;

    public StaffServiceImpl(CommonMapper commonMapper) {
        this.commonMapper = commonMapper;
    }

    @Override
    public List<StaffDto> getAllStaff() {
        String url = filmServiceUrl + "/staffs";
        Optional<StaffDto[]> listStaffDtoOptional = RestTemplateUtils.get(url,StaffDto[].class);
        return commonMapper.toList(listStaffDtoOptional);
    }

    @Override
    public List<StaffDto> getAllActorsStaff() {
        String url = filmServiceUrl + "/staffs/actors";
        Optional<StaffDto[]> listStaffDtoOptional = RestTemplateUtils.get(url,StaffDto[].class);
        return commonMapper.toList(listStaffDtoOptional);
    }

    @Override
    public List<StaffDto> getAllProducersStaff() {
        String url = filmServiceUrl + "/staffs/producerss";
        Optional<StaffDto[]> listStaffDtoOptional = RestTemplateUtils.get(url,StaffDto[].class);
        return commonMapper.toList(listStaffDtoOptional);
    }

    @Override
    public void deleteAllStaff() {
        String url = filmServiceUrl + "/staffs";
        RestTemplateUtils.delete(url);
    }

    @Override
    public Optional<StaffDto> getStaffById(Long staffId) {
        String url = filmServiceUrl + "/staffs/" + staffId;
        return RestTemplateUtils.get(url, null, StaffDto.class);
    }

    @Override
    public Optional<StaffDto> addStaff(StaffDto staffDto) {
        String url = filmServiceUrl + "/staffs";
        return RestTemplateUtils.post(url, staffDto, StaffDto.class);
    }

    @Override
    public void deleteStaffById(Long staffId) {
        String url = filmServiceUrl + "/staffs/" + staffId;
        RestTemplateUtils.delete(url);
    }

    @Autowired
    public void setFilmServiceUrl(@Value("${endpoint.film-service.url}") String filmServiceUrl) {
        this.filmServiceUrl = filmServiceUrl;
    }
}
