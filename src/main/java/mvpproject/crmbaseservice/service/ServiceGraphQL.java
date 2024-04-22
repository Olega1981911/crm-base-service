package mvpproject.crmbaseservice.service;

import lombok.RequiredArgsConstructor;
import mvpproject.crmbaseservice.model.dto.ClientDTO;
import mvpproject.crmbaseservice.model.mapper.ClientConverter;
import mvpproject.crmbaseservice.repository.ClientRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ServiceGraphQL {
    private final ClientRepository clientRepository;
   private final ClientConverter clientConverter;

    public ClientDTO getClient(Long id) {
        return clientRepository.findById(id).map(clientConverter::convertFromClientEntityToDto).orElse(ClientDTO.builder().build());
    }
}
