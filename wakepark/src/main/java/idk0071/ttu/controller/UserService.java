package idk0071.ttu.controller;

import idk0071.ttu.ridecount.RideCount;
import idk0071.ttu.ridecount.RideCountRepository;
import idk0071.ttu.user.User;
import idk0071.ttu.user.UserRepository;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class UserService {
    public static String changeUserRides(JSONObject request, UserRepository userRepository,
                                     RideCountRepository rideCountRepository) throws JSONException {
        JSONObject answer = new JSONObject();
        String username = request.getString("user");
        String newRides = request.getString("rides");
        User user = userRepository.findByUsername(username);
        RideCount rideCount = rideCountRepository.findByClient(user);
        answer.put("ridecount", rideCount.getRideId());
        rideCount.setRidesLeft(Integer.valueOf(newRides));
        answer.put("response", "successful");
        answer.put("newRides", newRides);
        return answer.toString();
    }

    public static String getUserData(JSONObject request, UserRepository userRepository,
                                     RideCountRepository rideCountRepository) throws JSONException {
        JSONObject answer = new JSONObject();
        String username = request.getString("username");
        if (userRepository.existsByUsername(username)) {
            answer.put("response", "successful");
            User user = userRepository.findByUsername(username);
            answer.put("firstName", user.getFirstName());
            answer.put("lastName", user.getLastName());
            answer.put("email", user.getEmail());
            answer.put("phoneNr", user.getPhoneNr());
            RideCount rideCount = rideCountRepository.findByClient(user);
            answer.put("ridesLeft", rideCount.getRidesLeft());
        } else {
            answer.put("response", "unsuccessful");
        }
        return answer.toString();
    }


    public static String login(JSONObject request, UserRepository userRepository) throws JSONException {
        JSONObject answer = new JSONObject();
        String username = request.getString("username");
        String password = request.getString("password");
        if (userExists(username, password, userRepository)) {
            answer.put("response", "successful");
            answer.put("status", getUserStatus(username, userRepository));
        } else {
            answer.put("response", "unsuccessful");
        }
        return answer.toString();
    }

    public static boolean userExists(String username, String password, UserRepository userRepository) {
        if (userRepository.existsByUsername(username)) {
            User user = userRepository.findByUsername(username);
            return user.getPassword().equals(password);
        }
        return false;
    }

    public static int getUserStatus(String username, UserRepository userRepository) {
        User user = userRepository.findByUsername(username);
        return user.getStatus();
    }

    public static String register(JSONObject request, UserRepository userRepository,
                                  RideCountRepository rideCountRepository) throws JSONException {
        JSONObject answer = new JSONObject();
        String firstName = request.getString("firstName");
        String userRides = request.getString("rides");
        String lastName = request.getString("lastName");
        String username = request.getString("username");
        String password = request.getString("password");
        String email = request.getString("email");
        String phoneNr = request.getString("phoneNr");
        if (!usernameTaken(username, userRepository)) {
            User newUser = new User();
            newUser.setStatus(1);
            newUser.setFirstName(firstName);
            newUser.setLastName(lastName);
            newUser.setUsername(username);
            newUser.setPassword(password);
            newUser.setEmail(email);
            newUser.setPhoneNr(phoneNr);
            addUser(newUser, userRepository);
            RideCount rideCount = new RideCount();
            rideCount.setClient(newUser);
            rideCount.setRidesLeft(Integer.valueOf(userRides));
            addRideCount(rideCount, rideCountRepository);
            answer.put("response", "successful");
        } else {
            answer.put("response", "unsuccessful");
        }
        return answer.toString();
    }

    public static User addUser(User user, UserRepository userRepository) {
        return userRepository.save(user);
    }

    public static RideCount addRideCount(RideCount rideCount, RideCountRepository rideCountRepository) {
        return rideCountRepository.save(rideCount);
    }

    public static boolean usernameTaken(String username, UserRepository userRepository) {
        List<User> users = userRepository.findAll();
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                return true;
            }
        }
        return false;
    }
}
