package mk.ukim.finki.diansvinarii.controller;

import ch.qos.logback.core.model.Model;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class LocationController {

    @PostMapping("/chooseLocation")
    public ResponseEntity<String> handleChosenLocation(@RequestBody Map<String, String> requestBody) {
        String location = requestBody.get("location");
        // Validate and process the location as needed

        return ResponseEntity.ok("Successfully chosen location: " + location);
    }
}
