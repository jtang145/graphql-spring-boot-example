package cn.naches.examples.graphql.pets.exception;

import graphql.ExceptionWhileDataFetching;
import graphql.GraphQLError;
import graphql.execution.DataFetcherExceptionHandler;
import graphql.execution.DataFetcherExceptionHandlerParameters;
import graphql.servlet.GraphQLErrorHandler;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Customize Error Handler
 */
@Component
public class PetGraphQLErrorHandler implements GraphQLErrorHandler {

    @Override
    public List<GraphQLError> processErrors(List<GraphQLError> list) {
        return list.stream().map(this::filterError).collect(Collectors.toList());
    }

    private GraphQLError filterError(GraphQLError graphQLError) {
        if(graphQLError instanceof ExceptionWhileDataFetching){
            ExceptionWhileDataFetching ex = (ExceptionWhileDataFetching) graphQLError;
            if(ex.getException() instanceof GraphQLError){
                Throwable filtered = ex.getException();
                // Hide stack trace to client
                filtered.setStackTrace(new StackTraceElement[0]);
                return (GraphQLError) filtered;
            }
        }
        return graphQLError;
    }
}
