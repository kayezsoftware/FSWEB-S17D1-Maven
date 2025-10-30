package com.workintech.fsswebs17d1.controller;
import org.springframework.http.ResponseEntity;
import com.workintech.fsswebs17d1.entity.Animal;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/workintech/animal")
public class AnimalController {

    // README: Value annotation ile çağır
    @Value("${course.name}")
    private String courseName;

    @Value("${project.developer.fullname}")
    private String developerName;

    private final Map<Integer, Animal> animals = new HashMap<>();

    public AnimalController() {
        animals.put(1, new Animal(1, "Lion"));
        animals.put(2, new Animal(2, "Eagle"));
    }

    @GetMapping
    public List<Animal> getAll() {
        return new ArrayList<>(animals.values());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Animal> getById(@PathVariable Integer id) {
        Animal found = animals.get(id);
        return (found != null) ? ResponseEntity.ok(found) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<String> add(@RequestBody Animal animal) {
        if (animal.getId() == null || animal.getName() == null || animal.getName().isBlank()) {
            return ResponseEntity.badRequest().body("id ve name zorunludur.");
        }
        animals.put(animal.getId(), animal);
        return ResponseEntity.ok("Animal added successfully!");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> update(@PathVariable Integer id, @RequestBody Animal body) {
        if (body.getId() == null) {
            return ResponseEntity.badRequest().body("Body.id zorunludur.");
        }

        if (!id.equals(body.getId())) {
            return ResponseEntity.badRequest().body("Path id ile body.id aynı olmalı.");
        }
        if (!animals.containsKey(id)) {
            return ResponseEntity.notFound().build();
        }
        animals.put(id, body);
        return ResponseEntity.ok("Animal updated successfully!");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Integer id) {
        if (animals.remove(id) != null) {
            return ResponseEntity.ok("Animal deleted successfully!");
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/info")
    public String getInfo() {
        return "Course: " + courseName + " | Developer: " + developerName;
    }
}
