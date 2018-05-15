package idk0071.ttu.controller;

import idk0071.ttu.reservation.Reservation;
import idk0071.ttu.reservation.ReservationRepository;
import idk0071.ttu.ridecount.RideCount;
import idk0071.ttu.ridecount.RideCountRepository;
import idk0071.ttu.track.Track;
import idk0071.ttu.track.TrackRepository;
import idk0071.ttu.user.User;
import idk0071.ttu.user.UserRepository;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;
    @Autowired
    private TrackRepository trackRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RideCountRepository rideCountRepository;

    public String addReservation(String body, RideCountService rideCountService) throws JSONException {
        JSONObject request = new JSONObject(body);
        JSONObject answer = new JSONObject();
        String client = request.getString("client");
        String startTime = request.getString("startTime");
        String trackName = request.getString("trackName");
        LocalDateTime rideStart = getRideStart(startTime);
        LocalDateTime rideEnd = rideStart.plusMinutes(15);
        Track track = trackRepository.findByName(trackName);
        if (!reservationRepository.existsByReservationStartAndTrack(rideStart, track)) {
            Reservation reservation = new Reservation();
            if (request.getString("action").equals("addReservationClient") || (!client.contains(" ") && userRepository.existsByUsername(client))) {
                User user = userRepository.findByUsername(client);
                RideCount ridesLeft = rideCountRepository.findByClient(user);
                if (ridesLeft.getRidesLeft() <= 0) {
                    answer.put("response", "unsuccessful");
                    return answer.toString();
                }
                reservation.setClient(user);
                reservation.setClientName(user.getFirstName() + " " + user.getLastName());
                rideCountService.decrementUserRideCount(user);
            } else if (!client.contains(" ") && !userRepository.existsByUsername(client)) {
                answer.put("response", "unsuccessful");
                return answer.toString();
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

    public String deleteReservation(String body, RideCountService rideCountService) throws JSONException {
        JSONObject request = new JSONObject(body);
        JSONObject answer = new JSONObject();
        String clientName = request.getString("clientName");
        Track track = trackRepository.findByName(request.getString("track"));
        Integer hour = request.getInt("hour");
        Integer minute = request.getInt("minute");
        List<Reservation> trackReservations = reservationRepository.findByClientNameAndTrack(clientName, track);
        LocalDateTime current = LocalDateTime.now();
        LocalDateTime check =
                LocalDateTime.of(current.getYear(), current.getMonth(), current.getDayOfMonth(), hour, minute, 0, 0);
        Reservation reservation = trackReservations.stream().filter(r -> r.getReservationStart().isEqual(check)).findFirst().get();
        if (reservation.getClient() != null
                && reservation.getReservationStart().minusMinutes(30).isAfter(LocalDateTime.now())) {
            rideCountService.incrementUserRideCount(reservation.getClient());
            answer.put("response", "successful");
        } else {
            answer.put("response", "unsuccessful");
        }
        reservationRepository.delete(reservation);
        return answer.toString();
    }

    public LocalDateTime getRideStart(String startTime) {
        LocalDateTime now = LocalDateTime.now();
        int startHour = Integer.valueOf(startTime.split(":")[0]);
        int startMinutes = Integer.valueOf(startTime.split(":")[1]);
        return LocalDateTime.of(now.getYear(), now.getMonth(), now.getDayOfMonth(), startHour, startMinutes, 0, 0);
    }

    private Comparator<Reservation> comparator = (left, right) -> {
        if (left.getReservationStart().isBefore(right.getReservationStart())) {
            return -1;
        } if (left.getReservationStart().isAfter(right.getReservationStart())) {
            return 1;
        } else {
            return 0;
        }
    };

    public List<Reservation> findActiveReservations() {
        List<Reservation> reservations = reservationRepository.findAll();
        LocalDateTime now = LocalDateTime.now().minusMinutes(5);
        return reservations.stream()
                        .filter(r -> r.getReservationStart().isAfter(now)).sorted(comparator).collect(Collectors.toList());
    }

}
