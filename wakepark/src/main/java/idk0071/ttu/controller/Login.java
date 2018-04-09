package idk0071.ttu.controller;

import idk0071.ttu.user.User;
import idk0071.ttu.user.UserRepository;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class Login {

    public static String respond(JSONObject request, UserRepository userRepository) throws JSONException {
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
}
