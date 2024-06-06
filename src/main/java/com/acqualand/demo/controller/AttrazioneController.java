package com.acqualand.demo.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.acqualand.demo.model.Attrazione;
import com.acqualand.demo.service.AttrazioneService;

import jakarta.transaction.Transactional;
@CrossOrigin
@RestController
@RequestMapping("/api/attrazioni")
public class AttrazioneController {

    @Autowired
    private AttrazioneService attrazioneService;

    @Transactional
    @GetMapping("/nearby")
    public List<Attrazione> getNearbyAttractions(
            @RequestParam double userLatitude,
            @RequestParam double userLongitude,
            @RequestParam int userAge,
            @RequestParam double userHeight) {
        List<Attrazione> allOpenAttractions = attrazioneService.getAllOpenAttractions();
        return allOpenAttractions.stream()
                .filter(attraction -> isWithinUserRequirements(attraction, userAge, userHeight))
                .sorted((a1, a2) -> Double.compare(
                        calculateDistance(userLatitude, userLongitude, a1.getLatitudine(), a1.getLongitudine()),
                        calculateDistance(userLatitude, userLongitude, a2.getLatitudine(), a2.getLongitudine())))
                .collect(Collectors.toList());
    }

    private boolean isWithinUserRequirements(Attrazione a, int userAge, double userHeight) {
        return userAge >= a.getMinAge() && userHeight >= a.getMinHeight();
    }

    private double calculateDistance(double lat1, double lon1, double lat2, double lon2) {
        int R = 6371;
        double latDistance = Math.toRadians(lat2 - lat1);
        double lonDistance = Math.toRadians(lon2 - lon1);
        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return R * c;
    }
}
