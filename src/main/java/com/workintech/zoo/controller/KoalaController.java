package com.workintech.zoo.controller;

import com.workintech.zoo.entity.Koala;
import com.workintech.zoo.exceptions.ZooException;
import jakarta.annotation.PostConstruct;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@RestController
@RequestMapping("/koalas")
public class KoalaController {
    private Map<Integer, Koala> koalas;
    @PostConstruct
    public void init(){
        koalas = new HashMap<>();
    }
    @GetMapping
    public List<Koala> getAllKoalas(){
       return koalas.values().stream().toList();
    }
    @GetMapping("/{id}")
    public Koala addKoalaById(@PathVariable int id){
        if(id <= 0){
            throw new ZooException("Id must be greater than 0", HttpStatus.BAD_REQUEST);
        }
        if(!koalas.containsKey(id)){
            throw new ZooException("Koala with given id is not exist: " + id, HttpStatus.NOT_FOUND);
        }


        return koalas.get(id);
    }
    @PostMapping
    public Koala addKoala(@RequestBody Koala koala){
        koalas.put(koala.getId(),koala);
        return koala;
    }
    @PutMapping("/{id}")
    public Koala update(@PathVariable int id, @RequestBody Koala koala){
        if (!koalas.containsKey(id)) throw new ZooException("Koala not found: " + id, HttpStatus.NOT_FOUND);
        koala.setId(id);
        koalas.put(id, koala);
        return koala;
    }
    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id){
        koalas.remove(id);
    }
}
