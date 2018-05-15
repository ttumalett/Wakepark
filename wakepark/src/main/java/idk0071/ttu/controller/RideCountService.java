package idk0071.ttu.controller;

import idk0071.ttu.ridecount.RideCount;
import idk0071.ttu.ridecount.RideCountRepository;
import idk0071.ttu.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RideCountService {

    @Autowired
    private RideCountRepository rideCountRepository;

    public void decrementUserRideCount(User user) {
        RideCount rideCount = rideCountRepository.findByClient(user);
        rideCount.setRidesLeft(rideCount.getRidesLeft() - 1);
        rideCountRepository.save(rideCount);
    }

    public void incrementUserRideCount(User user) {
        RideCount rideCount = rideCountRepository.findByClient(user);
        rideCount.setRidesLeft(rideCount.getRidesLeft() + 1);
        rideCountRepository.save(rideCount);
    }

    public void addRideCount(User newUser, String userRides) {
        RideCount rideCount = new RideCount();
        rideCount.setClient(newUser);
        rideCount.setRidesLeft(Integer.valueOf(userRides));
        rideCountRepository.save(rideCount);
    }
}
