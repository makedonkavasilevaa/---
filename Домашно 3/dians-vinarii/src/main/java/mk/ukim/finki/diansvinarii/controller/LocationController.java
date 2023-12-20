package mk.ukim.finki.diansvinarii.controller;

import ch.qos.logback.core.model.Model;
import com.fasterxml.jackson.annotation.JsonAlias;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class LocationController {

    static class LatLng {
        @JsonAlias({"lat", "latitude"})
        private double lat;

        @JsonAlias({"lng", "longitude"})
        private double lng;

        // getters and setters

        public double getLat() {
            return lat;
        }

        public void setLat(double lat) {
            this.lat = lat;
        }

        public double getLng() {
            return lng;
        }

        public void setLng(double lng) {
            this.lng = lng;
        }
    }

    @PostMapping("/navigate")
    public ResponseEntity<String> navigateToChosenLocation(@RequestBody Map<String, LatLng> locationData) {
        LatLng currentLocation = locationData.get("currentLocation");
        LatLng desiredLocation = locationData.get("desiredLocation");

        // Validate and process the locations as needed
        String response = generateGoogleMapsLink(currentLocation, desiredLocation);

        return ResponseEntity.ok(response);
    }

    private String generateGoogleMapsLink(LatLng currentLocation, LatLng desiredLocation) {
        // You can customize this link based on your requirements
        return "https://www.google.com/maps/dir/?api=1&origin=" +
                currentLocation.getLat() + "," + currentLocation.getLng() +
                "&destination=" + desiredLocation.getLat() + "," + desiredLocation.getLng();
    }
}