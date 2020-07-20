package cn.naches.examples.graphql.pets.exception;

import graphql.ErrorType;
import graphql.GraphQLError;
import graphql.language.SourceLocation;

import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * Make backend error status can be passed to client
 */
public class PetNotFoundException extends RuntimeException implements GraphQLError {
    private String fieldName;

    public PetNotFoundException(String message, String field){
        super(message);
        this.fieldName = field;
    }

    @Override
    public List<SourceLocation> getLocations() {
        return null;
    }

    @Override
    public ErrorType getErrorType() {
        return null;
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }

    @Override
    public Map<String, Object> getExtensions() {
        return Collections.singletonMap("invalidField", this.fieldName);
    }
}
