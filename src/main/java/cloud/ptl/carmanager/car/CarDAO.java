package cloud.ptl.carmanager.car;

import cloud.ptl.carmanager.client.ClientDAO;
import lombok.Data;

import javax.persistence.*;
import java.util.Collection;

@Data
@Entity
@Table(name = "car")
public class CarDAO {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String numberPlate;
    private String seatsNumber;
    private Double totalDistance;

    @ManyToOne(
            fetch = FetchType.LAZY
    )
    private ClientDAO rentedBy;
}
