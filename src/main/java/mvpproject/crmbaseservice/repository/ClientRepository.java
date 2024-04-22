package mvpproject.crmbaseservice.repository;

import mvpproject.crmbaseservice.model.entity.ClientEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClientRepository extends JpaRepository<ClientEntity, Long> {
    @Override
    Optional<ClientEntity> findById(Long aLong);
}
