package cn.naches.examples.graphql.pets.controller;

import cn.naches.examples.graphql.pets.entity.Pet;
import cn.naches.examples.graphql.pets.service.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class PetController {
    @Autowired
    PetService petService;

    @GetMapping(value = "/pets", produces = "application/json")
    public List<Pet> getPet(){
        List<Pet> pets = petService.findAllPets();
        return pets;
    }

}
