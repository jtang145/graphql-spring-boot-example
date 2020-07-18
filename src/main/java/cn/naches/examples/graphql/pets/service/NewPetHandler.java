package cn.naches.examples.graphql.pets.service;

import cn.naches.examples.graphql.pets.entity.Pet;

import java.util.concurrent.Callable;

public class NewPetHandler implements Callable<Pet> {
    private Pet pet;

    public NewPetHandler(Pet newPet){
        this.pet = newPet;
    }

    @Override
    public Pet call() throws Exception {
        Thread.sleep(1000l);
        return this.pet;
    }
}
