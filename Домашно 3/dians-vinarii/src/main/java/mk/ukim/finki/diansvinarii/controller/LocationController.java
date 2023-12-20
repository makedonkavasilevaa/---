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

    @PostMapping("/chooseLocation")
    public ResponseEntity<String> handleChosenLocation(@RequestBody LatLng latLng) {
        double latitude = latLng.getLat();
        double longitude = latLng.getLng();

        // Validate and process the latitude and longitude as needed
        String response = "Successfully chosen location. Latitude: " + latitude + ", Longitude: " + longitude;

        return ResponseEntity.ok(response);
    }

    @GetMapping("/navigate")
    public ResponseEntity<String> navigateToChosenLocation(@RequestParam double lat, @RequestParam double lng) {
        String googleMapsLink = generateGoogleMapsLink(lat, lng);
        return ResponseEntity.ok(googleMapsLink);
    }

    private String generateGoogleMapsLink(double lat, double lng) {
        // You can customize this link based on your requirements
        return "https://www.google.com/maps/dir/?api=1&destination=" + lat + "," + lng;
    }
}