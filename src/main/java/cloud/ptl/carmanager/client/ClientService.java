package cloud.ptl.carmanager.client;

import cloud.ptl.carmanager.car.CarDAO;
import cloud.ptl.carmanager.car.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.CommandLinePropertySource;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class ClientService {
    @Autowired
    private CarService carService;

    @Autowired
    private ClientRepository clientRepository;

    private ClientDAO create(ClientDAO clientDAO){
        return this.clientRepository.save(clientDAO);
    }

    private Boolean checkIfExists(ClientDAO clientDAO){
        if (clientDAO.getGovID() == null) return false;
        return this.clientRepository.existsByGovID(clientDAO.getGovID());
    }

    private Boolean checkIfExsit(Long id){
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
            throw new Exception("Not Found");
        return clientDAOOptional.get();
    }

    public Collection<ClientDAO> findAll(){
        return (Collection<ClientDAO>) this.clientRepository.findAll();
    }

    public ClientDAO rent(CarDAO carDAO, ClientDAO clientDAO){
        this.carService.rent(carDAO, clientDAO);
        return clientDAO;
    }

    public ClientDAO returnn(CarDAO carDAO, ClientDAO clientDAO, Double distanceCovered){
        this.carService.returnn(carDAO, distanceCovered);
        return clientDAO;
    }
}
