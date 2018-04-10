package idk0071.ttu.track;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface TrackRepository extends CrudRepository<Track, Long> {
    Track findByName(String name);
}
