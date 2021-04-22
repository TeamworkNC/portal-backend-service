package com.moviesandchill.portalbackendservice.dto.stream.state;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StateDto {

    private Long stateID;

    private String stateTitle;

}
