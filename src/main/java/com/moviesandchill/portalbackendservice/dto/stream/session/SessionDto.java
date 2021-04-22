package com.moviesandchill.portalbackendservice.dto.stream.session;

import com.moviesandchill.portalbackendservice.dto.stream.state.StateDto;
import com.moviesandchill.portalbackendservice.dto.stream.watcher.WatcherDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SessionDto {

    private Long sessionID;

    private Long filmID;

    private Long chatID;

    private Long organizerID;

    private LocalTime stopTime;

}
