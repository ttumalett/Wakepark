package idk0071.ttu.ridecount;

import idk0071.ttu.user.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RideCountRepository extends CrudRepository<RideCount, Long> {
    RideCount findByClient(User client);
}
