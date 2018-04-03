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
        } else {
            answer.put("response", "unsuccessful");
        }
        return answer.toString();
    }

    public static boolean userExists(String username, String password, UserRepository userRepository) {
        List<User> users = userRepository.findAll();
        for (User user : users) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }
}
