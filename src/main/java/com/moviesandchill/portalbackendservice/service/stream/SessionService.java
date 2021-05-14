package com.moviesandchill.portalbackendservice.service.stream;

import com.moviesandchill.portalbackendservice.dto.stream.session.NewSessionDto;
import com.moviesandchill.portalbackendservice.dto.stream.session.SessionDto;
import com.moviesandchill.portalbackendservice.dto.stream.session.SessionParDto;
import com.moviesandchill.portalbackendservice.dto.stream.watcher.WatcherDto;

import java.util.List;
import java.util.Optional;

public interface SessionService {

    List<SessionDto> getAllSession();

    void deleteAllSession();

    Optional<SessionDto> getSessionById(Long sessionID);

    Optional<SessionDto> addSession(NewSessionDto newSessionDto);

    void deleteSessionById(Long sessionID);

    Optional<SessionDto> addSessionByParameters(SessionParDto sessionParDto);

    List<WatcherDto> getAllWatcherWithSession(Long sessionID);

    void addWatcherToSession(Long watcherID, Long sessionID) throws Exception;

    void inviteFriendToSession(Long sessionID, Long userID);
}
