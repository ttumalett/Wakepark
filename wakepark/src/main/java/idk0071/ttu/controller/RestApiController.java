package idk0071.ttu.controller;

import idk0071.ttu.reservation.Reservation;
import idk0071.ttu.reservation.ReservationRepository;
import idk0071.ttu.ridecount.RideCountRepository;
import idk0071.ttu.track.TrackRepository;
import idk0071.ttu.user.UserRepository;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.time.LocalDateTime;
import java.util.List;

@org.springframework.web.bind.annotation.RestController
public class RestApiController {

    private UserRepository userRepository;
    private RideCountRepository rideCountRepository;
    private ReservationRepository reservationRepository;
    private TrackRepository trackRepository;

    public RestApiController(UserRepository userRepository, RideCountRepository rideCountRepository,
                             ReservationRepository reservationRepository, TrackRepository trackRepository1) {
        this.userRepository = userRepository;
        this.rideCountRepository = rideCountRepository;
        this.reservationRepository = reservationRepository;
        this.trackRepository = trackRepository1;
    }
    @CrossOrigin
    @RequestMapping(method = RequestMethod.POST, value = "")
    public String restService(@RequestBody String request) throws JSONException {
        JSONObject requestJson = new JSONObject(request);
        String action = requestJson.getString("action");
        if (action.equals("register")) {
            return UserService.register(requestJson, userRepository, rideCountRepository);
        } else if (action.equals("login")) {
            return UserService.login(requestJson, userRepository);
        } else if (action.equals("getUserData")) {
            return UserService.getUserData(requestJson, userRepository, rideCountRepository);
        } else if (action.equals("addReservation") || action.equals("addReservationWorker")) {
            return ReservationService.addReservation(requestJson, reservationRepository, trackRepository, userRepository);
        }
        return "proov";
    }
    @CrossOrigin
    @RequestMapping(method = RequestMethod.GET, value="/reservations")
    public List<Reservation> getReservations() {
        return reservationRepository.findReservationByReservationStartLessThanEqual(LocalDateTime.now());
    }
}
