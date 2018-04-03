package idk0071.ttu.track;

import idk0071.ttu.reservation.Reservation;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@Data
public class Track {
    @Id
    @GeneratedValue
    long trackId;
    String name;
    @OneToMany
    List<Reservation> reservations;
}
