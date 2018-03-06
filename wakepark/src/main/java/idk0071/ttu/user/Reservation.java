package idk0071.ttu.user;

import idk0071.ttu.park.Track;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
public class Reservation {
    @Id
    @GeneratedValue
    long reservationId;
    @ManyToOne
    User clientId;
    @ManyToOne
    Track trackId;
    LocalDateTime reservationStart;
    LocalDateTime reservationEnd;
}
