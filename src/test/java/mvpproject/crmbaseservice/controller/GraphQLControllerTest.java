package mvpproject.crmbaseservice.controller;


import mvpproject.crmbaseservice.TestData;
import mvpproject.crmbaseservice.model.dto.ClientDTO;
import mvpproject.crmbaseservice.model.mapper.ClientConverter;
import mvpproject.crmbaseservice.service.ServiceGraphQL;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;


import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(GraphQLController.class)
@AutoConfigureMockMvc
class GraphQLControllerTest extends TestData {
    @MockBean
    private ServiceGraphQL serviceGraphQL;
    @Autowired
    private MockMvc mockMvc;

    private Long clientId;
    private ClientDTO mockClient;

    @BeforeEach
    void init() {
        ModelMapper modelMapper = new ModelMapper();
        new ClientConverter(modelMapper);
        /*
        clientId = testClient().getId(); // Получение ID клиента из метода testClient()*/

        // Создание заглушки для клиента
        mockClient = new ClientDTO();
        mockClient.setId(1L);
        mockClient.setClientName("Ivan");

        clientId = mockClient.getId(); // Получение ID клиента из заглушки
    }



    @Test
    public void testExecute() throws Exception {
        when(serviceGraphQL.getClient(clientId)).thenReturn(mockClient);

        ResultActions perform = mockMvc.perform(post("/client/graphql")
                .contentType(MediaType.APPLICATION_JSON)
                .content(String.valueOf(clientId)));
        perform.andExpect(status().isOk())
                .andExpect(jsonPath("$.clientName").value(mockClient.getClientName()));
    }
}
