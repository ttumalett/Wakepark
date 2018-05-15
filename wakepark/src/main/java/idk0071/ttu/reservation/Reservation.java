package idk0071.ttu.reservation;

import idk0071.ttu.track.Track;
import idk0071.ttu.user.User;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Reservation {
    @Id
    @GeneratedValue
    long reservationId;
    @ManyToOne
    User client;
    String clientName;
    @ManyToOne
    Track track;
    LocalDateTime reservationStart;
    LocalDateTime reservationEnd;

    public User getClient() {
        return client;
    }

    public Track getTrack() {
        return track;
    }

    public LocalDateTime getReservationEnd() {
        return reservationEnd;
    }

    public LocalDateTime getReservationStart() {
        return reservationStart;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public void setClient(User client) {
        this.client = client;
    }

    public void setTrack(Track track) {
        this.track = track;
    }

    public void setReservationStart(LocalDateTime reservationStart) {
        this.reservationStart = reservationStart;
    }

    public void setReservationEnd(LocalDateTime reservationEnd) {
        this.reservationEnd = reservationEnd;
    }
}
