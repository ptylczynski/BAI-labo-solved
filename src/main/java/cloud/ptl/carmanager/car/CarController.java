package cloud.ptl.carmanager.car;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/car")
public class CarController {
    @Autowired
    private CarService carService;

    @GetMapping("/{id}")
    public CarDTO getOne(
            @PathVariable("id") Long id
    ) throws Exception {
        return CarDTO.toDTO(
                this.carService.getOne(id)
        );
    }

    @PostMapping("/{id}")
    public CarDTO postOne(
            @RequestBody CarDAO carDAO
    ){
        return CarDTO.toDTO(
                this.carService.createIfNotExits(carDAO)
        );
    }
}
