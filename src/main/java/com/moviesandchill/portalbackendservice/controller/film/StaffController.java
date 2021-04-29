package com.moviesandchill.portalbackendservice.controller.film;

import com.moviesandchill.portalbackendservice.dto.film.staff.StaffDto;
import com.moviesandchill.portalbackendservice.mapper.CommonMapper;
import com.moviesandchill.portalbackendservice.service.film.StaffService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping(
        path = "api/v1/staffs",
        produces = "application/json"
)
public class StaffController {

    private final StaffService staffService;
    private final CommonMapper commonMapper;

    public StaffController(StaffService staffService, CommonMapper commonMapper) {
        this.staffService = staffService;
        this.commonMapper = commonMapper;
    }

    @GetMapping
    public List<StaffDto> getAllStaff() {
        return staffService.getAllStaff();
    }

    @GetMapping("/actors")
    public List<StaffDto> getAllActorsStaff() {
        return staffService.getAllActorsStaff();
    }

    @GetMapping("/producerss")
    public List<StaffDto> getAllProducersStaff() {
        return staffService.getAllProducersStaff();
    }

    @DeleteMapping
    public void deleteAllStaff() {
        staffService.deleteAllStaff();
    }

    @GetMapping("/{staffId}")
    public ResponseEntity<StaffDto> getStaffById(@PathVariable Long staffId) {
        return commonMapper.toResponseEntity(staffService.getStaffById(staffId));
    }

    @PostMapping
    public ResponseEntity<StaffDto> addStaff(@RequestBody StaffDto staffDto) {
        return commonMapper.toResponseEntity(staffService.addStaff(staffDto));
    }

    @DeleteMapping("/{staffId}")
    public void deleteStaffById(@PathVariable Long staffId) {
        staffService.deleteStaffById(staffId);
    }

}
