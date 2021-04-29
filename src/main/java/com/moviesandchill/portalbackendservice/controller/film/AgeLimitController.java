package com.moviesandchill.portalbackendservice.controller.film;

import com.moviesandchill.portalbackendservice.dto.film.agelimit.AgeLimitDto;
import com.moviesandchill.portalbackendservice.mapper.CommonMapper;
import com.moviesandchill.portalbackendservice.service.film.AgeLimitService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping(
        path = "api/v1/ageLimits",
        produces = "application/json"
)
public class AgeLimitController {

    private final AgeLimitService ageLimitService;
    private final CommonMapper commonMapper;

    public AgeLimitController(AgeLimitService ageLimitService, CommonMapper commonMapper) {
        this.ageLimitService = ageLimitService;
        this.commonMapper = commonMapper;
    }

    @GetMapping
    public List<AgeLimitDto> getAllAgeLimit() {
        return ageLimitService.getAllAgeLimit();
    }

    @DeleteMapping
    @Secured("ROLE_ADMIN")
    public void deleteAllAgeLimit() {
        ageLimitService.deleteAllAgeLimit();
    }

    @GetMapping("/{ageLimitId}")
    public ResponseEntity<AgeLimitDto> getAgeLimitById(@PathVariable Long ageLimitId) {
        return commonMapper.toResponseEntity(ageLimitService.getAgeLimitById(ageLimitId));
    }

    @PostMapping
    public ResponseEntity<AgeLimitDto> addAgeLimit(@RequestBody AgeLimitDto ageLimitDto) {
        return commonMapper.toResponseEntity(ageLimitService.addAgeLimit(ageLimitDto));
    }

    @DeleteMapping("/{ageLimitId}")
    @Secured("ROLE_ADMIN")
    public void deleteAgeLimitById(@PathVariable Long ageLimitId) {
        ageLimitService.deleteAgeLimitById(ageLimitId);
    }

}
