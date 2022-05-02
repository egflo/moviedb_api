package com.moviedb_api.star;
;
import com.fasterxml.jackson.annotation.JsonView;
import com.moviedb_api.Views;
import com.moviedb_api.cast.Star_Movie;
import com.moviedb_api.cast.Star_MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller // This means that this class is a Controller
@RequestMapping(path="/cast") // This means URL's start with /movies (after Application path)
public class StarController {
    @Autowired // This means to get the bean called userRepository
    // Which is auto-generated by Spring, we will use it to handle the data
    private StarRepository starRepository;

    @Autowired
    private Star_MovieRepository star_movieRepository;


    @GetMapping(path="/all")
   // @JsonView(Views.Summary.class)
    public @ResponseBody
    Page<Star> getAllStars(
            @RequestParam Optional<Integer> page,
            @RequestParam Optional<Integer> limit,
            @RequestParam Optional<String> sortBy
    ) {
        // This returns a JSON or XML with the movies
        return starRepository.findAll(
                PageRequest.of(
                        page.orElse(0),
                        limit.orElse(5),
                        Sort.Direction.ASC, sortBy.orElse("name")
                )
        );
    }

    @GetMapping(path="/movie/{id}")
    public @ResponseBody Iterable<Star_Movie> getStarMovieByMovieId(
            @PathVariable(value = "id") String id) {
        // This returns a JSON or XML with the movies
        return star_movieRepository.findByMovieId(id);
    }

    @GetMapping(path="/name/{name}")
    //@JsonView(Views.Summary.class)
    public @ResponseBody
    Page<Star> findStarByName(
            @PathVariable String name,
            @RequestParam Optional<Integer> page,
            @RequestParam Optional<Integer> limit,
            @RequestParam Optional<String> sortBy) {
        // This returns a JSON or XML with the movies

        return starRepository.findByNameLike(
                name,
                PageRequest.of(
                        page.orElse(0),
                        limit.orElse(5),
                        Sort.Direction.ASC, sortBy.orElse("name")
                )
        );

        //return starRepository.findByNameLike(name);
    }

    @GetMapping("/{id}")
   // @JsonView(Views.Summary.class)
    public @ResponseBody Optional<Star> findStarById(
            @PathVariable(value = "id") String id)
    {
        return starRepository.findById(id);
    }
}