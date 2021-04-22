package com.moviesandchill.portalbackendservice.service.impl.stream;

import com.moviesandchill.portalbackendservice.dto.stream.session.SessionDto;
import com.moviesandchill.portalbackendservice.dto.stream.session.SessionParDto;
import com.moviesandchill.portalbackendservice.dto.stream.watcher.WatcherDto;
import com.moviesandchill.portalbackendservice.mapper.CommonMapper;
import com.moviesandchill.portalbackendservice.service.stream.SessionService;
import com.moviesandchill.portalbackendservice.utils.RestTemplateUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class SessionServiceImpl implements SessionService {

    private String streamServiceUrl;

    private final CommonMapper commonMapper;

    public SessionServiceImpl(CommonMapper commonMapper) {
        this.commonMapper = commonMapper;
    }


    @Override
    public List<SessionDto> getAllSession() {
        String url = streamServiceUrl + "/sessions";
        Optional<SessionDto[]> listSessionDtoOptional = RestTemplateUtils.get(url, SessionDto[].class);
        return commonMapper.toList(listSessionDtoOptional);
    }

    @Override
    public void deleteAllSession() {
        String url = streamServiceUrl + "/sessions";
        RestTemplateUtils.delete(url);
    }

    @Override
    public Optional<SessionDto> getSessionById(Long sessionID) {
        String url = streamServiceUrl + "/sessions/" + sessionID;
        return RestTemplateUtils.get(url, null, SessionDto.class);
    }

    @Override
    public Optional<SessionDto> addSession(SessionDto sessionDto) {
        String url = streamServiceUrl + "/sessions";
        return RestTemplateUtils.post(url, sessionDto, SessionDto.class);
    }

    @Override
    public void deleteSessionById(Long sessionID) {
        String url = streamServiceUrl + "/sessions/" + sessionID;
        RestTemplateUtils.delete(url);
    }

    @Override
    public Optional<SessionDto> addSessionByParameters(SessionParDto sessionParDto) {
        String url = streamServiceUrl + "/sessions/param";
        return RestTemplateUtils.post(url, sessionParDto, SessionDto.class);
    }

    @Override
    public List<WatcherDto> getAllWatcherWithSession(Long sessionID) {
        String url = streamServiceUrl + "/sessions/" + sessionID + "/watchers";
        Optional<WatcherDto[]> listWatcherDtoOptional = RestTemplateUtils.get(url, WatcherDto[].class);
        return commonMapper.toList(listWatcherDtoOptional);
    }

    @Override
    public void addWatcherToSession(Long watcherID, Long sessionID) throws Exception {
        String url = streamServiceUrl + "/sessions/" + sessionID + "/watchers/" + watcherID;
        RestTemplateUtils.post(url, null);
    }

    @Autowired
    public void setStreamServiceUrl(@Value("${endpoint.stream-service.url}") String streamServiceUrl) {
        this.streamServiceUrl = streamServiceUrl;
    }
}
