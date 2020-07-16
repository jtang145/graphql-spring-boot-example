package cn.naches.examples.graphql.pets.config;

import cn.naches.examples.graphql.pets.entity.Pet;
import cn.naches.examples.graphql.pets.service.PetService;
import com.coxautodev.graphql.tools.GraphQLRootResolver;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collections;
import java.util.List;

/**
 * The implementation from root resolver is required.
 * And the queries in root {@link Query} should also be exposed as public methods here.
 *
 */
public class Query implements GraphQLRootResolver {
    @Autowired
    PetService petService;

    public Query(){

    }

    public List<Pet> allPets(){
        return petService.findAllPets();
    }
}
