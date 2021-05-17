package com.moviesandchill.portalbackendservice.dto.user.user;

import com.moviesandchill.portalbackendservice.dto.film.film.FilmDto;
import com.moviesandchill.portalbackendservice.dto.film.film.FilmPageDto;
import com.moviesandchill.portalbackendservice.dto.film.staff.StaffDto;
import com.moviesandchill.portalbackendservice.dto.user.achievement.AchievementDto;
import com.moviesandchill.portalbackendservice.dto.user.globalrole.GlobalRoleDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FullUserDto {

    private Long userId;

    private String login;

    private String email;

    private LocalDate birthday;

    private String logoUrl;

    private String description;

    private LocalDate registrationDate;

    private List<AchievementDto> achievements;

    private List<FilmPageDto> favoriteFilms;

    private List<FilmDto> wantWatchFilms;

    private List<FilmDto> watchedFilms;

    private List<StaffDto> favoriteStaffs;

    private List<GlobalRoleDto> globalRoles;
}
