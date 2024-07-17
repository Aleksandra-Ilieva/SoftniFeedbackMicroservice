package org.example.softnifeedbackmicroservice.controller;

import org.example.softnifeedbackmicroservice.model.dto.FeedbackDto;
import org.example.softnifeedbackmicroservice.service.FeedbackService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/feedback")
public class FeedbackController {

    private final FeedbackService feedbackService;

    @Value("${api.key}")
    private String apiKey;

    public FeedbackController(FeedbackService feedbackService) {
        this.feedbackService = feedbackService;
    }

    private boolean isApiKeyValid(String requestApiKey) {
        return apiKey.equals(requestApiKey);
    }

    @PostMapping
    public ResponseEntity<FeedbackDto> saveFeedback(@RequestParam("api_key") String requestApiKey, @RequestBody FeedbackDto feedbackDto) {
        if (!isApiKeyValid(requestApiKey)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        this.feedbackService.saveFeedback(feedbackDto);
        return ResponseEntity.status(HttpStatus.OK).body(feedbackDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFeedback(@RequestParam("api_key") String requestApiKey, @PathVariable Long id) {
        if (!isApiKeyValid(requestApiKey)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        this.feedbackService.deleteFeedback(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping
    public ResponseEntity<List<FeedbackDto>> getAllFeedbacks(@RequestParam("api_key") String requestApiKey) {
        if (!isApiKeyValid(requestApiKey)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        return ResponseEntity.ok(this.feedbackService.getLastTen());
    }
}
