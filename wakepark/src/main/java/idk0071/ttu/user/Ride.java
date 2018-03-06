package idk0071.ttu.user;

import lombok.Data;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
public class Ride {
    @Id
    @GeneratedValue
    long rideId;
    User clientId;
    int ridesLeft;
}
