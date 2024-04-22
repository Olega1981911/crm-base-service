package mvpproject.crmbaseservice.config;

import com.coxautodev.graphql.tools.SchemaParser;
import graphql.GraphQL;

import graphql.schema.GraphQLSchema;
import mvpproject.crmbaseservice.resolver.ClientQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class GraphQLConfig {
    private final ClientQuery clientQuery;

    @Autowired
    public GraphQLConfig(ClientQuery clientQuery) {
        this.clientQuery = clientQuery;
    }

    @Bean
    public GraphQL graphQL() {
        GraphQLSchema schema = SchemaParser.newParser()
                .file("schema.graphql")
                .resolvers(clientQuery)
                .build()
                .makeExecutableSchema();

        return GraphQL.newGraphQL(schema).build();
    }
}
