package idk0071.ttu.controller;

import idk0071.ttu.ridecount.RideCountRepository;
import idk0071.ttu.user.UserRepository;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@org.springframework.web.bind.annotation.RestController
public class RestApiController {

    private UserRepository userRepository;
    private RideCountRepository rideCountRepository;

    public RestApiController(UserRepository userRepository, RideCountRepository rideCountRepository) {
        this.userRepository = userRepository;
        this.rideCountRepository = rideCountRepository;
    }

    @CrossOrigin
    @RequestMapping(method = RequestMethod.POST, value = "")
    public String restService(@RequestBody String request) throws JSONException {
        JSONObject requestJson = new JSONObject(request);
        String action = requestJson.getString("action");
        if (action.equals("register")) {
            return Register.respond(requestJson, userRepository, rideCountRepository);
        } else if (action.equals("login")) {
            return Login.respond(requestJson, userRepository);
        } else if (action.equals("showUserInfo")) {
            return UserProfile.respond(requestJson, userRepository, rideCountRepository);
        }
        return "proov";
    }
}
