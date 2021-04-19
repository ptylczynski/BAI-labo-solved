package cloud.ptl.carmanager.client;

import org.springframework.data.repository.CrudRepository;

public interface ClientRepository extends CrudRepository<ClientDAO, Long> {
    Boolean existsByGovID(String govID);
}
