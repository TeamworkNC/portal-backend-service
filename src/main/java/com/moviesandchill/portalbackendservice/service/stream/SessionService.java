package com.moviesandchill.portalbackendservice.service.stream;

import com.fasterxml.jackson.databind.JsonNode;
import com.moviesandchill.portalbackendservice.dto.stream.session.NewSessionDto;
import com.moviesandchill.portalbackendservice.dto.stream.session.SessionDto;
import com.moviesandchill.portalbackendservice.dto.stream.session.SessionParDto;
import com.moviesandchill.portalbackendservice.dto.stream.watcher.WatcherDto;

import java.util.List;
import java.util.Optional;

public interface SessionService {

    List<SessionDto> getAllSession();

    void deleteAllSession();

    Optional<SessionParDto> getSessionById(Long sessionID);

    Optional<SessionDto> addSession(NewSessionDto newSessionDto);

    void deleteSessionById(Long sessionID);

    void setSessionTimeAndState(Long sessionID, JsonNode jsonNode) throws Exception;

    Optional<SessionDto> addSessionByParameters(SessionParDto sessionParDto);

    List<WatcherDto> getAllWatcherWithSession(Long sessionID);

    void addWatcherToSession(Long watcherID, Long sessionID) throws Exception;

    void inviteFriendToSession(Long sessionID, Long userID);
}
