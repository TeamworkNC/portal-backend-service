package com.moviesandchill.portalbackendservice.dto.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto implements Serializable {
    private Long userId;

    private String login;

    private String email;

    private String firstName;

    private String lastName;

    private LocalDate birthday;

    private String logoUrl;

    private String description;

    private LocalDate registrationDate;
}
