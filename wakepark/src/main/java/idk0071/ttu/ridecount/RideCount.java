package idk0071.ttu.ridecount;

import idk0071.ttu.user.User;
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
    User client;
    int ridesLeft;

    public void setClient(User client) {
        this.client = client;
    }

    public void setRideId(long rideId) {
        this.rideId = rideId;
    }

    public void setRidesLeft(int ridesLeft) {
        this.ridesLeft = ridesLeft;
    }

    public int getRidesLeft() {
        return ridesLeft;
    }

    public long getRideId() {
        return rideId;
    }

    public User getClient() {
        return client;
    }

    public void incrementCount(int rides) {
        this.ridesLeft += rides;
    }

    public void decrementCount() {
        this.ridesLeft -= 1;
    }
}
