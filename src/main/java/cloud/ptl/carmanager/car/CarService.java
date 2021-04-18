package cloud.ptl.carmanager.car;

import cloud.ptl.carmanager.client.ClientDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class CarService {
    @Autowired
    private CarRepository carRepository;

    private CarDAO create(CarDAO carDAO){
        return this.carRepository.save(carDAO);
    }

    private Boolean checkIfExists(CarDAO carDAO){
        if(carDAO.getNumberPlate() == null) return false;
        return
                this.carRepository.existsByNumberPlate(carDAO.getNumberPlate());
    }

    public CarDAO createIfNotExits(CarDAO carDAO){
        if(this.checkIfExists(carDAO))
            return carDAO;
        else
            return this.create(carDAO);
    }

    public CarDAO getOne(Long id) throws Exception {
        Optional<CarDAO> optionalCarDAO =
                this.carRepository.findById(id);
        if(optionalCarDAO.isEmpty())
            throw new Exception("Car not found");
        else return optionalCarDAO.get();
    }

    public Collection<CarDAO> getAllRentedBy(ClientDAO clientDAO){
        return this.carRepository.findAllByRentedBy(clientDAO);
    }
}
