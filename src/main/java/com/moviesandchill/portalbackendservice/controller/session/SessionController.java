package com.moviesandchill.portalbackendservice.controller.session;

import com.moviesandchill.portalbackendservice.dto.stream.session.NewSessionDto;
import com.moviesandchill.portalbackendservice.dto.stream.session.SessionDto;
import com.moviesandchill.portalbackendservice.dto.stream.session.SessionParDto;
import com.moviesandchill.portalbackendservice.dto.stream.watcher.WatcherDto;
import com.moviesandchill.portalbackendservice.mapper.CommonMapper;
import com.moviesandchill.portalbackendservice.service.stream.SessionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping(
        path = "api/v1/sessions",
        produces = "application/json"
)
public class SessionController {

    private final SessionService sessionService;
    private final CommonMapper commonMapper;

    public SessionController(SessionService sessionService, CommonMapper commonMapper) {
        this.sessionService = sessionService;
        this.commonMapper = commonMapper;
    }

    @GetMapping
    public List<SessionDto> getAllSession() {
        return sessionService.getAllSession();
    }

    @DeleteMapping
    public void deleteAllSession() {
        sessionService.deleteAllSession();
    }

    @GetMapping("/{sessionID}")
    public ResponseEntity<SessionDto> getSessionById(@PathVariable Long sessionID) {
        return commonMapper.toResponseEntity(sessionService.getSessionById(sessionID));
    }

    @PostMapping()
    public ResponseEntity<SessionDto> addSession(@RequestBody NewSessionDto newSessionDto) {
        return commonMapper.toResponseEntity(sessionService.addSession(newSessionDto));
    }

    @DeleteMapping("/{sessionID}")
    public void deleteSessionById(@PathVariable Long sessionID) {
        sessionService.deleteSessionById(sessionID);
    }

    @PostMapping("/param")
    public ResponseEntity<SessionDto> addSessionByParameters(@RequestBody SessionParDto sessionParDto) {
        return commonMapper.toResponseEntity(sessionService.addSessionByParameters(sessionParDto));
    }

    @GetMapping("/{sessionID}/watchers")
    public List<WatcherDto> getAllWatcherWithSession(@PathVariable Long sessionID) {
        return sessionService.getAllWatcherWithSession(sessionID);
    }

    @PostMapping("/{sessionID}/addWatcher/{watcherID}")
    public void addWatcherToSession(@PathVariable Long sessionID, @PathVariable Long watcherID) throws Exception {
        sessionService.addWatcherToSession(watcherID, sessionID);
    }

    @PostMapping("/{sessionID}/invite")
    public void inviteFriendToSession(@PathVariable Long sessionID, @RequestBody Long friendID) {
        sessionService.inviteFriendToSession(sessionID, friendID);
    }
}
