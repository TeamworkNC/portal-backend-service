package com.moviesandchill.portalbackendservice.dto.film.staff;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StaffDto {

    private Long id_staff;

    private String staff_first_name;

    private String staff_last_name;

    private LocalDate birthday_date;

    private String description;
}
