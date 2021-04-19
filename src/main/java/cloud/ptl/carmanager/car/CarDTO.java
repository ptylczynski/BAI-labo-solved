package cloud.ptl.carmanager.car;

import cloud.ptl.carmanager.client.ClientDAO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
public class CarDTO {
    private Long id;
    private String numberPlate;
    private String seatsNumber;
    private Double totalDistance;

    public static CarDTO toDTO(CarDAO entity){
        return CarDTO.builder()
                .id(entity.getId())
                .numberPlate(entity.getNumberPlate())
                .seatsNumber(entity.getSeatsNumber())
                .totalDistance(entity.getTotalDistance())
                .build();
    }
}
