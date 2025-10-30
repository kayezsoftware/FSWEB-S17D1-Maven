package com.workintech.fsswebs17d1.controller;

import com.workintech.fsswebs17d1.entity.Animal;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/workintech/animal")
public class AnimalController {

    @Value("${course.name}")
    private String courseName;

    @Value("${project.developer.fullname}")
    private String developerName;

    private Map<Integer, Animal> animals = new HashMap<>();

    @GetMapping("/info")
    public String getDeveloperInfo() {
        return "Course: " + courseName + " | Developer: " + developerName;
    }

    @GetMapping
    public List<Animal> getAllAnimals() {
        return new ArrayList<>(animals.values());
    }

    @GetMapping("/{id}")
    public Animal getAnimalById(@PathVariable int id) {
        return animals.get(id);
    }

    @PostMapping
    public String addAnimal(@RequestBody Animal animal) {
        animals.put(animal.getId(), animal);
        return "Animal added successfully!";
    }

    @PutMapping("/{id}")
    public String updateAnimal(@PathVariable int id, @RequestBody Animal animal) {
        animals.put(id, animal);
        return "Animal updated successfully!";
    }

    @DeleteMapping("/{id}")
    public String deleteAnimal(@PathVariable int id) {
        animals.remove(id);
        return "Animal deleted successfully!";
    }
}
