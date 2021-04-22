package com.moviesandchill.portalbackendservice.dto.stream.session;

import com.moviesandchill.portalbackendservice.dto.stream.state.StateDto;
import com.moviesandchill.portalbackendservice.dto.stream.watcher.WatcherDto;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class FullSessionDto {

    private Long sessionID;

    private Long filmID;

    private Long chatID;

    private Long organizerID;

    private LocalTime stopTime;

    private StateDto state;

    private List<WatcherDto> watchers = new ArrayList<>();
}
