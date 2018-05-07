package idk0071.ttu.controller;

import idk0071.ttu.reservation.Reservation;
import idk0071.ttu.reservation.ReservationRepository;
import idk0071.ttu.ridecount.RideCountRepository;
import idk0071.ttu.track.TrackRepository;
import idk0071.ttu.user.UserRepository;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RestApiController {

    @Autowired
    private UserService userService;
    @Autowired
    private ReservationService reservationService;

    @CrossOrigin
    @RequestMapping(method = RequestMethod.POST, value = "")
    public String restService(@RequestBody String request) throws JSONException {
        JSONObject requestJson = new JSONObject(request);
        String action = requestJson.getString("action");
        if (action.equals("addReservation") || action.equals("addReservationWorker")) {
            return reservationService.addReservation(requestJson);
        }
        return "proov";
    }

    @CrossOrigin
    @RequestMapping(method = RequestMethod.POST, value = "/changeUserRides")
    public String changeUserRides(@RequestBody String body) throws JSONException {
        return userService.changeUserRides(body);
    }

    @CrossOrigin
    @RequestMapping(method = RequestMethod.POST, value = "/getUserData")
    public String getUserData(@RequestBody String body) throws JSONException {
        return userService.getUserData(body);
    }

    @CrossOrigin
    @RequestMapping(method = RequestMethod.POST, value = "/register")
    public String registerUser(@RequestBody String body) throws JSONException {
        return userService.register(body);
    }

    @CrossOrigin
    @RequestMapping(method = RequestMethod.POST, value = "/login")
    public String loginUser(@RequestBody String body) throws JSONException {
        return userService.login(body);
    }

    @CrossOrigin
    @RequestMapping(method = RequestMethod.GET, value="/reservations")
    public List<Reservation> getReservations() {
        return reservationService.findActiveReservations();
    }
}
