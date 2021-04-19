package cloud.ptl.carmanager.client;

import cloud.ptl.carmanager.ProcessingException;
import cloud.ptl.carmanager.car.CarDAO;
import cloud.ptl.carmanager.car.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.CommandLinePropertySource;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

@Service
public class ClientService {
    @Autowired
    private CarService carService;

    @Autowired
    private ClientRepository clientRepository;

    private ClientDAO create(ClientDAO clientDAO){
        if (clientDAO.getRentedCars() == null)
            clientDAO.setRentedCars(new ArrayList<>());
        return this.clientRepository.save(clientDAO);
    }

    private Boolean checkIfExists(ClientDAO clientDAO){
        if (clientDAO.getGovID() == null) return false;
        return this.clientRepository.existsByGovID(clientDAO.getGovID());
    }

    private Boolean checkIfExist(Long id){
        return this.clientRepository.existsById(id);
    }

    public ClientDAO createIfNotExist(ClientDAO clientDAO){
        if (this.checkIfExists(clientDAO))
            return clientDAO;
        else
            return this.create(clientDAO);
    }

    public ClientDAO getOne(Long id) throws Exception {
        Optional<ClientDAO> clientDAOOptional =
                this.clientRepository.findById(id);
        if (clientDAOOptional.isEmpty())
            throw new ProcessingException("Not Found");
        return clientDAOOptional.get();
    }

    public Collection<ClientDAO> findAll(){
        return (Collection<ClientDAO>) this.clientRepository.findAll();
    }

    public ClientDAO rent(CarDAO carDAO, ClientDAO clientDAO) throws Exception {
        this.carService.rent(carDAO, clientDAO);
        return clientDAO;
    }

    public ClientDAO rent(Long carId, Long clientId) throws Exception {
        return this.rent(
                this.carService.getOne(carId),
                this.getOne(clientId)
        );
    }

    public ClientDAO returnn(CarDAO carDAO, ClientDAO clientDAO, Double distanceCovered) throws Exception {
        this.carService.returnn(carDAO, clientDAO, distanceCovered);
        return clientDAO;
    }

    public ClientDAO returnn(Long carId, Long clientId, Double distance) throws Exception {
        return this.returnn(
                this.carService.getOne(carId),
                this.getOne(clientId),
                distance
        );
    }
}
