package com.moviesandchill.portalbackendservice.dto.user;

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

    private String firstName;

    private String lastName;

    private LocalDate birthday;

    private String logoUrl;

    private String description;

    private LocalDate registrationDate;

    private List<UserDto> friends;

    private List<AchievementDto> achievements;

//    private Set<FilmDto> favoriteFilms;
//
//    private Set<FilmDto> wantWatchFilms;
//
//    private Set<FilmDto> watchedFilms;
//
//    private Set<StaffDto> favoriteStaffs;

    private List<GlobalRoleDto> globalRoles;
}
