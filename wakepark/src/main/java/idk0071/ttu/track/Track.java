package idk0071.ttu.track;

import idk0071.ttu.reservation.Reservation;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
public class Track {
    @Id
    @GeneratedValue
    long trackId;
    String name;
    @OneToMany
    List<Reservation> reservations;

    public void setName(String name) {
        this.name = name;
    }

    public void setReservations(List<Reservation> reservations) {
        this.reservations = reservations;
    }
    public void addReservation(Reservation reservation) {
        reservations.add(reservation);
    }

    public void removeReservation(Reservation reservation) {
        reservations.remove(reservation);
    }

    public void setTrackId(long trackId) {
        this.trackId = trackId;
    }

    public long getTrackId() {
        return trackId;
    }

    public String getName() {
        return name;
    }

    public List<Reservation> getReservations() {
        return reservations;
    }
}
