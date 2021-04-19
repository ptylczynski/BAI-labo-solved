package cloud.ptl.carmanager.client;

import cloud.ptl.carmanager.car.CarDAO;
import cloud.ptl.carmanager.car.CarDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.stream.Collectors;

@Data
@Builder
@AllArgsConstructor
public class ClientDTO {
    private Long id;
    private String name;
    private String surname;
    private String govID;
    private LocalDateTime lastSeen;
    private Collection<CarDTO> rentedCars;

    public static ClientDTO toDTO(ClientDAO entity){
        return ClientDTO.builder()
                .id(entity.getId())
                .name(entity.getName())
                .surname(entity.getSurname())
                .govID(entity.getGovID())
                .lastSeen(entity.getLastSeen())
                .rentedCars(
                        entity.getRentedCars().stream()
                                .map(CarDTO::toDTO)
                                .collect(Collectors.toList())
                )
                .build();
    }
}
