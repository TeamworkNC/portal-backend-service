package com.moviesandchill.portalbackendservice.dto.user.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NewUserDto {
    private String login;

    private String email;

    private LocalDate birthday;

    private String password;
}
