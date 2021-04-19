package cloud.ptl.carmanager.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/clinet")
public class ClientController {
    @Autowired
    private ClientService clientService;

    @GetMapping("/{id}")
    public ClientDTO getOne(
            @PathVariable("id") Long id
    ) throws Exception {
        return ClientDTO.toDTO(
                this.clientService.getOne(id)
        );
    }

    @PostMapping("/")
    public ClientDTO postOne(
            @RequestBody ClientDAO clientDAO
    ){
        return ClientDTO.toDTO(
                this.clientService.createIfNotExist(clientDAO)
        );
    }

    @PatchMapping("/rent/{car_id}")
    public ClientDTO retnOne(
            @PathVariable("car_id") Long carId,
            @RequestParam("client_id") Long clientId
    ) throws Exception {
        return ClientDTO.toDTO(
                this.clientService.rent(carId, clientId)
        );
    }

    @PatchMapping("/return/{car_id}")
    public ClientDTO returnOne(
            @PathVariable("car_id") Long carId,
            @RequestParam("client_id") Long clientId,
            @RequestParam("distance") Double distance
    ) throws Exception {
        return ClientDTO.toDTO(
                this.clientService.returnn(
                        carId, clientId, distance)
        );
    }
}
