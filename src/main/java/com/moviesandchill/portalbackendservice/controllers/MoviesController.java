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
    public void setProductToProductForm(MoviesToMoviesForm moviesToMoviesForm) {
        this.moviesToMoviesForm = moviesToMoviesForm;
    }

    @Autowired
    public void setProductService(MoviesService moviesService) {
        this.moviesService = moviesService;
    }

    @RequestMapping("/")
    public String redirToList(){
        return "redirect:/movies/list";
    }

    @RequestMapping({"/movies/list", "/movies"})
    public String listProducts(Model model){
        model.addAttribute("movies", moviesService.listAll());
        return "movies/list";
    }

    @RequestMapping("/movies/show/{id}")
    public String getProduct(@PathVariable String id, Model model){
        model.addAttribute("movies", moviesService.getById(Long.valueOf(id)));
        return "movies/show";
    }

    @RequestMapping("movies/edit/{id}")
    public String edit(@PathVariable String id, Model model){
        Movies movies = moviesService.getById(Long.valueOf(id));
        MoviesForm moviesForm = moviesToMoviesForm.convert(movies);

        model.addAttribute("productForm", moviesForm);
        return "movies/moviesform";
    }

    @RequestMapping("/product/new")
    public String newProduct(Model model){
        model.addAttribute("productForm", new MoviesForm());
        return "movies/moviesform";
    }

    @RequestMapping(value = "/product", method = RequestMethod.POST)
    public String saveOrUpdateProduct(@Valid MoviesForm moviesForm, BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            return "movies/moviesform";
        }

        Movies savedMovie = moviesService.saveOrUpdateProductForm(moviesForm);

        return "redirect:/movies/show/" + savedMovie.getId();
    }

    @RequestMapping("/movies/delete/{id}")
    public String delete(@PathVariable String id){
        moviesService.delete(Long.valueOf(id));
        return "redirect:/movies/list";
    }
}
