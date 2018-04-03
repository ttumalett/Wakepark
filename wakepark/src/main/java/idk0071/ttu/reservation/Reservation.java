package idk0071.ttu.reservation;

import idk0071.ttu.track.Track;
import idk0071.ttu.user.User;
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
