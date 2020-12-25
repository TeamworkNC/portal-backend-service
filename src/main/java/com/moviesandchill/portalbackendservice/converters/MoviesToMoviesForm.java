package com.moviesandchill.portalbackendservice.converters;

import com.moviesandchill.portalbackendservice.commands.MoviesForm;
import com.moviesandchill.portalbackendservice.domain.Movies;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class MoviesToMoviesForm implements Converter<Movies, MoviesForm> {
    @Override
    public MoviesForm convert(Movies movies) {
        MoviesForm moviesForm = new MoviesForm();
        moviesForm.setId(movies.getId());
        moviesForm.setDescription(movies.getDescription());
        moviesForm.setName(movies.getName());
        return moviesForm;
    }
}
