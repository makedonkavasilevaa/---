package mk.ukim.finki.diansvinarii.controller;

import ch.qos.logback.core.model.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LocationController {

    @GetMapping("/chooseLocation")
    public String showChooseLocationForm() {
        return "chooseLocationForm";
    }

    @PostMapping("/chooseLocation")
    public String handleChosenLocation(@RequestParam String location, Model model) {
        // Assuming the user provides a location as a string
        // You can perform validation and processing here
        model.addAttribute("chosenLocation", location);
        return "redirect:/navigate";
    }

    @GetMapping("/navigate")
    public String navigateToChosenLocation(Model model) {
        // Access the chosen location from the model or from a service
        String chosenLocation = (String) model.getAttribute("chosenLocation");
        model.addAttribute("chosenLocation", chosenLocation);
        return "navigate";
    }
}
