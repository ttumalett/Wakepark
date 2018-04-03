package idk0071.ttu.controller;

import idk0071.ttu.ridecount.RideCount;
import idk0071.ttu.ridecount.RideCountRepository;
import idk0071.ttu.user.User;
import idk0071.ttu.user.UserRepository;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class Register {

    public static String respond(JSONObject request, UserRepository userRepository,
                                 RideCountRepository rideCountRepository) throws JSONException {
        JSONObject answer = new JSONObject();
        String firstName = request.getString("firstName");
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
            rideCount.setRidesLeft(0);
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
