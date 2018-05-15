package idk0071.ttu.reservation;

import idk0071.ttu.track.Track;
import idk0071.ttu.user.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ReservationRepository extends CrudRepository<Reservation, Long> {
    boolean existsByReservationStartAndTrack(LocalDateTime localDateTime, Track track);
    List<Reservation> findAll();
    List<Reservation> findByClient(User user);
    List<Reservation> findByClientNameAndTrack(String clientName, Track track);
    void delete(Reservation reservation);
}
