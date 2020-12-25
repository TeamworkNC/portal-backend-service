package com.moviesandchill.portalbackendservice.controllers;

import com.moviesandchill.portalbackendservice.commands.MoviesForm;
import com.moviesandchill.portalbackendservice.converters.MoviesToMoviesForm;
import com.moviesandchill.portalbackendservice.domain.Movies;
import com.moviesandchill.portalbackendservice.services.MoviesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@Controller
public class MoviesController {
    private MoviesService moviesService;

    private MoviesToMoviesForm moviesToMoviesForm;

    @Autowired
    public void setMoviesToMoviesForm(MoviesToMoviesForm moviesToMoviesForm) {
        this.moviesToMoviesForm = moviesToMoviesForm;
    }

    @Autowired
    public void setMoviesService(MoviesService moviesService) {
        this.moviesService = moviesService;
    }

    @RequestMapping("/")
    public String redirToList(){
        return "redirect:/movies/list";
    }

    @RequestMapping({"/movies/list", "/movies"})
    public String listMovies(Model model){
        model.addAttribute("movies", moviesService.listAll());
        return "movies/list";
    }

    @RequestMapping("/movies/show/{id}")
    public String getMovies(@PathVariable String id, Model model){
        model.addAttribute("movies", moviesService.getById(Long.valueOf(id)));
        return "movies/show";
    }

    @RequestMapping("movies/edit/{id}")
    public String edit(@PathVariable String id, Model model){
        Movies movies = moviesService.getById(Long.valueOf(id));
        MoviesForm moviesForm = moviesToMoviesForm.convert(movies);

        model.addAttribute("moviesForm", moviesForm);
        return "movies/moviesform";
    }

    @RequestMapping("/movies/new")
    public String newMovies(Model model){
        model.addAttribute("moviesForm", new MoviesForm());
        return "movies/moviesform";
    }

    @RequestMapping(value = "/movies", method = RequestMethod.POST)
    public String saveOrUpdateMovies(@Valid MoviesForm moviesForm, BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            return "movies/moviesform";
        }

        Movies savedMovie = moviesService.saveOrUpdateMoviesForm(moviesForm);

        return "redirect:/movies/show/" + savedMovie.getId();
    }

    @RequestMapping("/movies/delete/{id}")
    public String delete(@PathVariable String id){
        moviesService.delete(Long.valueOf(id));
        return "redirect:/movies/list";
    }
}
