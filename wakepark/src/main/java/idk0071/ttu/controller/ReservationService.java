package idk0071.ttu.controller;

import idk0071.ttu.reservation.Reservation;
import idk0071.ttu.reservation.ReservationRepository;
import idk0071.ttu.track.Track;
import idk0071.ttu.track.TrackRepository;
import idk0071.ttu.user.User;
import idk0071.ttu.user.UserRepository;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

@Service
public class ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;
    @Autowired
    private TrackRepository trackRepository;
    @Autowired
    private UserRepository userRepository;

    public String addReservation(JSONObject request)
            throws JSONException {
        JSONObject answer = new JSONObject();
        String client = request.getString("client");
        String startTime = request.getString("startTime");
        String trackName = request.getString("trackName");
        LocalDateTime rideStart = getRideStart(startTime);
        LocalDateTime rideEnd = rideStart.plusMinutes(15);
        Track track = trackRepository.findByName(trackName);
        if (!reservationRepository.existsByReservationStartAndTrack(rideStart, track)) {
            Reservation reservation = new Reservation();
            if (request.getString("action").equals("addReservation")) {
                User user = userRepository.findById(Integer.valueOf(client));
                reservation.setClient(user);
            } else {
                reservation.setClientName(client);
            }
            reservation.setReservationStart(rideStart);
            reservation.setReservationEnd(rideEnd);
            reservation.setTrack(track);
            reservationRepository.save(reservation);
            answer.put("response", "successful");
        } else {
            answer.put("response", "unsuccessful");
        }
        return answer.toString();
    }

    public LocalDateTime getRideStart(String startTime) {
        LocalDateTime now = LocalDateTime.now();
        int startHour = Integer.valueOf(startTime.split(":")[0]);
        int startMinutes = Integer.valueOf(startTime.split(":")[1]);
        return LocalDateTime.of(now.getYear(), now.getMonth(), now.getDayOfMonth(), startHour, startMinutes);
    }

    public List<Reservation> findActiveReservations() {
        return reservationRepository.findAll();
    }
}
