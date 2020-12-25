package com.moviesandchill.portalbackendservice.services;

import com.moviesandchill.portalbackendservice.commands.MoviesForm;
import com.moviesandchill.portalbackendservice.domain.Movies;

import java.util.List;

public interface MoviesService {
    List<Movies> listAll();

    Movies getById(Long id);

    Movies saveOrUpdate(Movies movie);

    void delete(Long id);

    Movies saveOrUpdateProductForm(MoviesForm productForm);
}
