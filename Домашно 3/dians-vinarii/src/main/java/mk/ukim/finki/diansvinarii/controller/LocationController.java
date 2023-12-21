package mk.ukim.finki.diansvinarii.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class LocationController {

    static class LatLng {
        private double lat;
        private double lng;

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

    @PostMapping("/directions")
    public ResponseEntity<Object> getDirections(@RequestBody Map<String, LatLng> locationData) {
        LatLng startLocation = locationData.get("startLocation");
        LatLng endLocation = locationData.get("endLocation");

        // Validate and process the locations as needed

        // Calculate directions using a simple algorithm (Haversine formula)
        double[] coordinates = calculateDirections(startLocation, endLocation);

        return ResponseEntity.ok(coordinates);
    }

    private double[] calculateDirections(LatLng startLocation, LatLng endLocation) {
        // Haversine formula for distance calculation
        double R = 6371.0; // Radius of the Earth in kilometers
        double lat1 = Math.toRadians(startLocation.getLat());
        double lon1 = Math.toRadians(startLocation.getLng());
        double lat2 = Math.toRadians(endLocation.getLat());
        double lon2 = Math.toRadians(endLocation.getLng());

        double dlat = lat2 - lat1;
        double dlon = lon2 - lon1;

        double a = Math.sin(dlat / 2) * Math.sin(dlat / 2) +
                Math.cos(lat1) * Math.cos(lat2) *
                        Math.sin(dlon / 2) * Math.sin(dlon / 2);

        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        double distance = R * c;

        // For simplicity, return a list of coordinates representing the path
        // In a real-world scenario, you might want to use a more advanced algorithm
        // Here, we're returning an array of two coordinates: start and end
        return new double[]{startLocation.getLat(), startLocation.getLng(), endLocation.getLat(), endLocation.getLng()};
    }
}