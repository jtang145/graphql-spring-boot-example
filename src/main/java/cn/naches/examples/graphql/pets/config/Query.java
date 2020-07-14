package cn.naches.examples.graphql.pets.config;

import cn.naches.examples.graphql.pets.bean.Pet;
import com.coxautodev.graphql.tools.GraphQLRootResolver;

import java.util.Collections;
import java.util.List;

/**
 * The implementation from root resolver is required.
 * And the queries in root {@link Query} should also be exposed as public methods here.
 *
 */
public class Query implements GraphQLRootResolver {

    public Query(){

    }

    public List<Pet> allPets(){
        return Collections.emptyList();
    }
}
