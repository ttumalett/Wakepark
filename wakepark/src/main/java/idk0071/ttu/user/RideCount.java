package idk0071.ttu.user;

import lombok.Data;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
@Data
public class RideCount {
    @Id
    @GeneratedValue
    long rideId;
    @OneToOne
    User clientId;
    int ridesLeft;
}
