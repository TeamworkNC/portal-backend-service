package com.moviesandchill.portalbackendservice.service.film.impl;

import com.moviesandchill.portalbackendservice.service.film.FilmService;
import com.moviesandchill.portalbackendservice.service.film.FilmUploadContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FilmUploadContentServiceImpl implements FilmUploadContentService {

    private String filmServiceUrl;

    @Override
    public void uploadFilmPoster(long filmId, MultipartFile file) throws Exception {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);

        MultiValueMap<String, Object> body
                = new LinkedMultiValueMap<>();
        body.add("file", file.getResource());

        HttpEntity<MultiValueMap<String, Object>> requestEntity
                = new HttpEntity<>(body, headers);

        String url = filmServiceUrl + "/filmContent/" + filmId + "/uploadPoster";
        new RestTemplate().postForEntity(url, requestEntity, Void.class);
    }

    @Override
    public void uploadFilmScreenshot(long filmId, MultipartFile file) throws Exception {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);

        MultiValueMap<String, Object> body
                = new LinkedMultiValueMap<>();
        body.add("file", file.getResource());

        HttpEntity<MultiValueMap<String, Object>> requestEntity
                = new HttpEntity<>(body, headers);

        String url = filmServiceUrl + "/filmContent/" + filmId + "/uploadScreenshot";
        new RestTemplate().postForEntity(url, requestEntity, Void.class);
    }

    @Override
    public void uploadFilmVideo(long filmId, MultipartFile file) throws Exception {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);

        MultiValueMap<String, Object> body
                = new LinkedMultiValueMap<>();
        body.add("file", file.getResource());

        HttpEntity<MultiValueMap<String, Object>> requestEntity
                = new HttpEntity<>(body, headers);

        String url = filmServiceUrl + "/filmContent/" + filmId + "/uploadVideo";
        new RestTemplate().postForEntity(url, requestEntity, Void.class);
    }

    @Autowired
    public void setFilmServiceUrl(@Value("${endpoint.film-service.url}") String filmServiceUrl) {
        this.filmServiceUrl = filmServiceUrl;
    }
}
