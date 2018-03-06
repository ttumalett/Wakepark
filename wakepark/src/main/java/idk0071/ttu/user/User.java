package idk0071.ttu.user;

import lombok.Data;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@Data
public class User {
    @Id
    @GeneratedValue
    long id;
    String status;
    String firstName;
    String lastName;
    String email;
    String phoneNr;
    @OneToMany
    List<Reservation> reservations;
}
