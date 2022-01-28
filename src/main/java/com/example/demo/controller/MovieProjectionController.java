package com.example.demo.controller;

import com.example.demo.model.IS_.MovieGenreCombination;
import com.example.demo.model.Movie;
import com.example.demo.model.MovieProjection;
import com.example.demo.repository.MovieGenreCombinationRepository;
import com.example.demo.service.MovieProjectionService;
import com.example.demo.service.MovieService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/projections")
public class MovieProjectionController {
    private final MovieProjectionService movieProjectionService;
    private final MovieService movieService;
    private final MovieGenreCombinationRepository movieGenreCombinationRepository;
    public MovieProjectionController(MovieProjectionService movieProjectionService, MovieService movieService, MovieGenreCombinationRepository movieGenreCombinationRepository) {
        this.movieProjectionService = movieProjectionService;
        this.movieService = movieService;
        this.movieGenreCombinationRepository = movieGenreCombinationRepository;
    }

    @GetMapping
    public String getProjectionPage(@RequestParam(required = false) String error, Model model) {
        if (error != null && !error.isEmpty()) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
        }
        List<MovieProjection> movieprojection = this.movieProjectionService.findAll();
        model.addAttribute("movieprojection", movieprojection);
        model.addAttribute("bodyContent", "projections");
        return "master-template";
    }
    @PreAuthorize("hasRole('ROLE_EMPLOYEE')")
    @GetMapping("/add-form")
    public String addProjectionPage(Model model) {
        List<Movie> movie = this.movieService.findAll();
        model.addAttribute("movie", movie);
        model.addAttribute("bodyContent", "add-projection");
        return "master-template";
    }
    @PreAuthorize("hasRole('ROLE_EMPLOYEE')")
    @PostMapping("/add")
    public String saveProjection(
            @RequestParam(required = false) Integer projection_id,
            @RequestParam String projection_movie_start,
            @RequestParam String projection_movie_end,
            @RequestParam String projection_screening_date,
            @RequestParam String projection_type,
            @RequestParam Float projection_price,
            @RequestParam Integer movie_id) {
            this.movieProjectionService.save(projection_id, projection_movie_start, projection_movie_end, projection_screening_date, projection_type, projection_price, movie_id);
            return "redirect:/projections";
    }
}
