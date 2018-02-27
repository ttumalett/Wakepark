package idk0071.ttu.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@org.springframework.web.bind.annotation.RestController
public class RestApiController {

    @CrossOrigin
    @RequestMapping(method = RequestMethod.POST, value = "")
    public String restService(@RequestBody String request) {
        return "Proov";
    }
}
