package cn.naches.examples.graphql.pets.service;

import cn.naches.examples.graphql.pets.entity.Pet;
import cn.naches.examples.graphql.pets.repository.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PetService {
    @Autowired
    PetRepository petRepository;


    public List<Pet> findAllPets() {
        return petRepository.findAll();
    }
}
