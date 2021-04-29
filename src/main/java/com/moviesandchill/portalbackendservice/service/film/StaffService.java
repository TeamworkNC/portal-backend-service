package com.moviesandchill.portalbackendservice.service.film;

import com.moviesandchill.portalbackendservice.dto.film.staff.StaffDto;

import java.util.List;
import java.util.Optional;

public interface StaffService {

    List<StaffDto> getAllStaff();

    List<StaffDto> getAllActorsStaff();

    List<StaffDto> getAllProducersStaff();

    void deleteAllStaff();

    Optional<StaffDto> getStaffById(Long staffId);

    Optional<StaffDto> addStaff(StaffDto staffDto);

    void deleteStaffById(Long staffId);
}
