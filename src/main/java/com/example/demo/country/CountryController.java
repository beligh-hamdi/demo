package com.example.demo.country;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping("/")
public class CountryController {

    private final CountryRepository repository;

    public CountryController(CountryRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<Country> getTreeById() {
        return StreamSupport.stream(
                Spliterators.spliteratorUnknownSize(repository.findAll().iterator(), Spliterator.ORDERED), false)
                .toList();
    }

    @GetMapping("/save")
    public void saveAll() {
        List<Country> countries = Arrays.asList(
            Country.builder().id("1").name("Canada").build(),
            Country.builder().id("2").name("Tunisia").build(),
            Country.builder().id("3").name("France").build()
        );
        repository.saveAll(countries);
    }
}
