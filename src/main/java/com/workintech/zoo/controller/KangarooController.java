package com.workintech.zoo.controller;

import com.workintech.zoo.entity.Kangaroo;
import com.workintech.zoo.exceptions.ZooException;
import jakarta.annotation.PostConstruct;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/kangaroos")
public class KangarooController {
    private Map<Integer, Kangaroo> kangaroos;
    @PostConstruct
    public void init(){
        kangaroos = new HashMap<>();
    }
    @GetMapping
    public List<Kangaroo> findAll(){
        return new ArrayList<>(kangaroos.values());
    }
    @GetMapping("/{id}")
    public Kangaroo getKangarooById(@PathVariable int id){
        if(id <= 0){
            throw new ZooException("Id must be greater than 0", HttpStatus.BAD_REQUEST);
        }
        if(!kangaroos.containsKey(id)){
            throw new ZooException("Kangaro with given id is not exist: " + id, HttpStatus.NOT_FOUND);
        }
        return kangaroos.get(id);
    }
    @PostMapping
    public Kangaroo addKangaroo(@RequestBody Kangaroo kangaroo){
        if (kangaroo.getId() <= 0) {
            throw new ZooException("Id is required", HttpStatus.BAD_REQUEST);
        }
        kangaroos.put(kangaroo.getId(), kangaroo);
        return kangaroo;

    }
    @PutMapping("/{id}")
    public Kangaroo update(@PathVariable int id, @RequestBody Kangaroo kangaroo){
        if (!kangaroos.containsKey(id)) throw new ZooException("Kangaroo not found: " + id, HttpStatus.NOT_FOUND);
        kangaroo.setId(id);
        kangaroos.put(id, kangaroo);
        return kangaroo;
    }
    @DeleteMapping("/{id}")
    public Kangaroo delete(@PathVariable int id) {
        Kangaroo removed = kangaroos.remove(id);
        if (removed == null) throw new ZooException("Kangaroo not found: " + id, HttpStatus.NOT_FOUND);
        return removed;
    }
}
