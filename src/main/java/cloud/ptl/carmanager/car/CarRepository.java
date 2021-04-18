package cloud.ptl.carmanager.car;

import cloud.ptl.carmanager.client.ClientDAO;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;

public interface CarRepository extends CrudRepository<CarDAO, Long> {
    Boolean existsByNumberPlate(String numberPlate);
    Collection<CarDAO> findAllByRentedBy(ClientDAO clientDAO);
}
