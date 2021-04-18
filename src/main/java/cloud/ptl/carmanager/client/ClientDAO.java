package cloud.ptl.carmanager.client;

import cloud.ptl.carmanager.car.CarDAO;
import lombok.Data;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Collection;

@Entity
@Data
@Table(name = "client")
public class ClientDAO {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String surname;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @LastModifiedDate
    private LocalDateTime lastSeen;

    @OneToMany(
            mappedBy = "rentedBy",
            fetch = FetchType.LAZY
    )
    Collection<CarDAO> rentedCars;
}
