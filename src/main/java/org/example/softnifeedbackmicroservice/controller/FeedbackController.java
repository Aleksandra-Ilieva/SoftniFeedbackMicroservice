package org.example.softnifeedbackmicroservice.controller;

import org.example.softnifeedbackmicroservice.model.dto.FeedbackDto;
import org.example.softnifeedbackmicroservice.model.entity.Feedback;
import org.example.softnifeedbackmicroservice.service.FeedbackService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/feedback")
public class FeedbackController {

    private final FeedbackService feedbackService;

    public FeedbackController(FeedbackService feedbackService) {
        this.feedbackService = feedbackService;
    }


    @PostMapping
    public ResponseEntity<FeedbackDto> saveFeedback(@RequestBody FeedbackDto feedbackDto) {
     this.feedbackService.saveFeedback(feedbackDto);
     return ResponseEntity.status(HttpStatus.OK).body(feedbackDto);
    }

    @DeleteMapping("/{id}")
    public void deleteFeedback(@PathVariable Long id) {
        this.feedbackService.deleteFeedback(id);
    }

    @GetMapping
    List<FeedbackDto> getAllFeedbacks() {
       return this.feedbackService.getLastTen();
    }
}
