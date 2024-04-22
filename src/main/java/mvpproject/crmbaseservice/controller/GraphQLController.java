package mvpproject.crmbaseservice.controller;


import lombok.RequiredArgsConstructor;
import mvpproject.crmbaseservice.erorr.SalesNotFoundException;
import mvpproject.crmbaseservice.model.dto.ClientDTO;
import mvpproject.crmbaseservice.service.ServiceGraphQL;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/client")
@RequiredArgsConstructor
public class GraphQLController {

    private final ServiceGraphQL serviceGraphQL;

    @PostMapping(value = "/graphql")
    public ResponseEntity<ClientDTO> getClientById(@RequestBody Long id) {
        try {
            var response = serviceGraphQL.getClient(id);
            if (response != null) {
                return ResponseEntity.ok(response);
            } else {
                throw new SalesNotFoundException("Client with id " + id + " not found");
            }
        } catch (SalesNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ClientDTO());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ClientDTO());
        }
    }


}

