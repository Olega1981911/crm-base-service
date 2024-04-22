package mvpproject.crmbaseservice.resolver;


import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import io.leangen.graphql.annotations.GraphQLArgument;
import io.leangen.graphql.annotations.GraphQLQuery;
import mvpproject.crmbaseservice.model.dto.ClientDTO;
import mvpproject.crmbaseservice.service.ServiceGraphQL;
import org.springframework.stereotype.Component;


@Component
public class ClientQuery implements GraphQLQueryResolver {
    private final ServiceGraphQL serviceGraphQL;

    public ClientQuery(ServiceGraphQL serviceGraphQL) {
        this.serviceGraphQL = serviceGraphQL;
    }

    @GraphQLQuery(name = "client")
    public ClientDTO client(@GraphQLArgument(name = "id")Long id) {
        return serviceGraphQL.getClient(id);
    }

}
