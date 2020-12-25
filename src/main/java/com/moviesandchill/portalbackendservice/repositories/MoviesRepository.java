package com.moviesandchill.portalbackendservice.repositories;

import com.moviesandchill.portalbackendservice.domain.Movies;
import org.springframework.data.repository.CrudRepository;

public interface MoviesRepository extends CrudRepository<Movies, Long> {
}
