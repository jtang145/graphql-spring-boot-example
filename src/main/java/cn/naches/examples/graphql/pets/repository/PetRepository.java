package cn.naches.examples.graphql.pets.repository;

import cn.naches.examples.graphql.pets.entity.Pet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PetRepository extends JpaRepository<Pet, Integer> {
    Pet findAllByName(String name);
}
