package org.example.softnifeedbackmicroservice.service;


import org.example.softnifeedbackmicroservice.model.dto.FeedbackDto;

import java.util.List;

public interface FeedbackService {

    FeedbackDto saveFeedback(FeedbackDto contactUsDto);

    void deleteFeedback(FeedbackDto contactUsDto);
    void deleteFeedback(Long id);
    List<FeedbackDto> getLastTen();
}
