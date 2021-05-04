package com.moviesandchill.portalbackendservice.dto.stream.session;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NewSessionDto {
    private Long filmID;

    private Long organizerID;
}
