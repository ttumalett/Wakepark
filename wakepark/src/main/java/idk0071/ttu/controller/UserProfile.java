package idk0071.ttu.controller;

import idk0071.ttu.ridecount.RideCount;
import idk0071.ttu.ridecount.RideCountRepository;
import idk0071.ttu.user.User;
import idk0071.ttu.user.UserRepository;
import org.json.JSONException;
import org.json.JSONObject;

public class UserProfile {

    public static String respond(JSONObject request, UserRepository userRepository,
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
}
