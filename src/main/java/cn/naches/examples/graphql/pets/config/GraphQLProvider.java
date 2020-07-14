package cn.naches.examples.graphql.pets.config;

import graphql.GraphQL;
import graphql.schema.GraphQLSchema;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;


import javax.annotation.PostConstruct;
import java.io.IOException;

import com.coxautodev.graphql.tools.SchemaParser;

@Component
public class GraphQLProvider {
    private GraphQL graphQL;

    @Bean
    public GraphQL graphQL(){
        return graphQL;
    }

    @PostConstruct
    public void init(){
        GraphQLSchema graphQLSchema = buildSchema();
        this.graphQL = GraphQL.newGraphQL(graphQLSchema).build();
    }

    private GraphQLSchema buildSchema() {
        return SchemaParser.newParser()
                .file("schema.graphqls")
                .resolvers(
                        new Query())
                .build()
                .makeExecutableSchema();
    }
}
