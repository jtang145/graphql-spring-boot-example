package cn.naches.examples.graphql.pets.service;

import cn.naches.examples.graphql.pets.entity.Pet;
import cn.naches.examples.graphql.pets.repository.PetRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

@Service
public class PetService {
    private static final Logger logger = LoggerFactory.getLogger(PetService.class);

    @Autowired
    PetRepository petRepository;

    @Autowired
    @Qualifier("newPetThreadPool")
    private ExecutorService newPetThreadPool;

    private BlockingQueue<Future<Pet>> newPetQueue = null;

    public List<Pet> findAllPets() {
        return petRepository.findAll();
    }

    public Pet addPet(String name) {
        Pet exists = petRepository.findAllByName(name);
        if(exists != null){
            throw new RuntimeException("Pet " + name + " is already exist!");
        }
        Pet newPet = new Pet();
        newPet.setName(name);
        Pet savedPet = petRepository.saveAndFlush(newPet);
        notifyNewPet(savedPet);
        return savedPet;
    }

    public void initNewPetQueue(BlockingQueue<Future<Pet>> newPetQueue) {
        this.newPetQueue = newPetQueue;
    }

    private void notifyNewPet(Pet pet){
        if(this.newPetQueue != null){
            try {
                this.newPetQueue.put(newPetThreadPool.submit(new NewPetHandler(pet)));
                logger.info("New Pet Added: " + pet.getName());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }else{
            throw new IllegalStateException("New pet queue is not initialized.");
        }
    }
}
