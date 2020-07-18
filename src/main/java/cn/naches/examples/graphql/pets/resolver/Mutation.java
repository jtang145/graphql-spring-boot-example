package cn.naches.examples.graphql.pets.resolver;

import cn.naches.examples.graphql.pets.entity.Pet;
import cn.naches.examples.graphql.pets.service.PetService;
import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Mutation implements GraphQLMutationResolver {
    @Autowired
    PetService petService;

    public Pet addPet(String name){
        return petService.addPet(name);
    }

    public Pet updatePet(int id, String name, Integer age){
        return petService.updatePet(id, name, age);
    }
}
