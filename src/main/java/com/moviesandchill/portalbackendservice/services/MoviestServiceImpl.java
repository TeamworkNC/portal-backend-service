package com.moviesandchill.portalbackendservice.services;

import com.moviesandchill.portalbackendservice.commands.MoviesForm;
import com.moviesandchill.portalbackendservice.converters.MoviesFormToMovies;
import com.moviesandchill.portalbackendservice.domain.Movies;
import com.moviesandchill.portalbackendservice.repositories.MoviesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MoviestServiceImpl implements MoviesService {
    private MoviesRepository moviesRepository;
    private MoviesFormToMovies moviesFormToMovies;

    @Autowired
    public MoviestServiceImpl(MoviesRepository moviesRepository, MoviesFormToMovies moviesFormToMovies) {
        this.moviesRepository = moviesRepository;
        this.moviesFormToMovies = moviesFormToMovies;
    }


    @Override
    public List<Movies> listAll() {
        List<Movies> movies = new ArrayList<>();
        moviesRepository.findAll().forEach(movies::add); //fun with Java 8
        return movies;
    }

    @Override
    public Movies getById(Long id) {
        return moviesRepository.findById(id).orElse(null);
    }

    @Override
    public Movies saveOrUpdate(Movies movies) {
        moviesRepository.save(movies);
        return movies;
    }

    @Override
    public void delete(Long id) {
        moviesRepository.deleteById(id);

    }

    @Override
    public Movies saveOrUpdateProductForm(MoviesForm moviesForm) {
        Movies savedMovies = saveOrUpdate(moviesFormToMovies.convert(moviesForm));

        System.out.println("Saved Product Id: " + savedMovies.getId());
        return savedMovies;
    }
}
