package idk0071.ttu.controller;

import idk0071.ttu.ridecount.RideCount;
import idk0071.ttu.ridecount.RideCountRepository;
import idk0071.ttu.user.User;
import idk0071.ttu.user.UserRepository;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RideCountRepository rideCountRepository;

    public String changeUserRides(String body) throws JSONException {
        JSONObject answer = new JSONObject();
        JSONObject userData = new JSONObject(body);
        String username = userData.getString("user");
        String newRides = userData.getString("rides");
        User user = userRepository.findByUsername(username);
        RideCount rideCount = rideCountRepository.findByClient(user);
        answer.put("ridecount", rideCount.getRideId());
        rideCount.setRidesLeft(Integer.valueOf(newRides));
        rideCountRepository.save(rideCount);
        answer.put("response", "successful");
        answer.put("newRides", newRides);
        return answer.toString();
    }

    public String getUserData(String body) throws JSONException {
        JSONObject userData = new JSONObject(body);
        JSONObject answer = new JSONObject();
        String username = userData.getString("username");
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


    public String login(String body) throws JSONException {
        JSONObject userData = new JSONObject(body);
        JSONObject answer = new JSONObject();
        String username = userData.getString("username");
        String password = userData.getString("password");
        if (userExists(username, password)) {
            answer.put("response", "successful");
            answer.put("username", username);
            answer.put("status", getUserStatus(username));
        } else {
            answer.put("response", "unsuccessful");
        }
        return answer.toString();
    }

    public boolean userExists(String username, String password) {
        if (userRepository.existsByUsername(username)) {
            User user = userRepository.findByUsername(username);
            return user != null && user.getPassword().equals(password);
        }
        return false;
    }

    public int getUserStatus(String username) {
        User user = userRepository.findByUsername(username);
        if (user==null) {
            return 0;
        }
        return user.getStatus();
    }

    public String register(String body, RideCountService rideCountService) throws JSONException {
        JSONObject request = new JSONObject(body);
        JSONObject answer = new JSONObject();
        String firstName = request.getString("firstName");
        String userRides = request.getString("rides");
        String lastName = request.getString("lastName");
        String username = request.getString("username");
        String password = request.getString("password");
        String email = request.getString("email");
        String phoneNr = request.getString("phoneNr");
        if (!usernameTaken(username)) {
            User newUser = new User();
            newUser.setStatus(1);
            newUser.setFirstName(firstName);
            newUser.setLastName(lastName);
            newUser.setUsername(username);
            newUser.setPassword(password);
            newUser.setEmail(email);
            newUser.setPhoneNr(phoneNr);
            addUser(newUser);
            rideCountService.addRideCount(newUser, userRides);
            answer.put("response", "successful");
        } else {
            answer.put("response", "unsuccessful");
        }
        return answer.toString();
    }

    private void addUser(User user) {
        userRepository.save(user);
    }

    public boolean usernameTaken(String username) {
        List<User> users = userRepository.findAll();
        if (users == null) {
            return false;
        }
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                return true;
            }
        }
        return false;
    }
}
