package com.moviesandchill.portalbackendservice.converters;

import com.moviesandchill.portalbackendservice.commands.MoviesForm;
import com.moviesandchill.portalbackendservice.domain.Movies;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class MoviesFormToMovies implements Converter<MoviesForm, Movies> {
    @Override
    public Movies convert(MoviesForm moviesForm) {
        Movies movies = new Movies();
        if (moviesForm.getId() != null  && !StringUtils.isEmpty(moviesForm.getId())) {
            movies.setId(new Long(moviesForm.getId()));
        }
        movies.setDescription(moviesForm.getDescription());
        movies.setName(moviesForm.getName());
        return movies;
    }
}
