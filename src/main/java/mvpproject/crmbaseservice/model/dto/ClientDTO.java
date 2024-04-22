package mvpproject.crmbaseservice.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Optional;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClientDTO {

    private Long id;
    private String clientName;
    private Optional<String> address;
    private String payerAccountNumber;
    private String bankDetails;
}
