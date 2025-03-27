package com.lungdiseasedetector.lung_disease_detector.PredictionService;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.client.RestTemplate;

import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

@Service
public class PredictionService {

    public String predict(MultipartFile file) {
        try {
            byte[] imageBytes = file.getBytes();
            String base64Image = Base64.getEncoder().encodeToString(imageBytes);
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            Map<String, Object> request = new HashMap<>();
            request.put("image", base64Image);
            RestTemplate restTemplate = new RestTemplate();
        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(request, headers);


        @SuppressWarnings("rawtypes")
        ResponseEntity<Map> response = restTemplate.postForEntity(
                "https://4721-35-237-185-90.ngrok-free.app/predict",
                entity,
                Map.class
        );

        System.out.println("HTTP Status Code: " + response.getStatusCode());
        System.out.println("Response Body: " + response.getBody());
        @SuppressWarnings("unchecked")
        Map<String, String> map = response.getBody();
        

            return map.get("disease");

        } catch (Exception e) {
            e.printStackTrace();
            return "Error in prediction sending from spring boot";
        }
    }
}
