package cn.naches.examples.graphql.pets.resolver;

import cn.naches.examples.graphql.pets.entity.Pet;
import cn.naches.examples.graphql.pets.service.PetService;
import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * The implementation from root resolver is required.
 * And the queries in root {@link Query} should also be exposed as public methods here.
 *
 */
@Component
public class Query implements GraphQLQueryResolver {
    @Autowired
    PetService petService;

    public Query(){

    }

    public List<Pet> allPets(){
        return petService.findAllPets();
    }
}
