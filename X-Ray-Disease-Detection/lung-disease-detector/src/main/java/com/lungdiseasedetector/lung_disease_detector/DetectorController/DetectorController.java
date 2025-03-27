package com.lungdiseasedetector.lung_disease_detector.DetectorController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.lungdiseasedetector.lung_disease_detector.PredictionService.PredictionService;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/predict")
@CrossOrigin(origins = "*") // Allow frontend requests
public class DetectorController {

    private final PredictionService predictionService = new PredictionService();

    @PostMapping
    public ResponseEntity<Map<String, String>> predict(@RequestParam("file") MultipartFile file) {
        String disease = predictionService.predict(file);
        
        Map<String, String> response = new HashMap<>();
        response.put("disease", disease);

        return ResponseEntity.ok(response);
    }
}
