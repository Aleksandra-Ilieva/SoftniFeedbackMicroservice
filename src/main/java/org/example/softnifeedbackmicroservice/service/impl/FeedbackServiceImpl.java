package org.example.softnifeedbackmicroservice.service.impl;


import org.example.softnifeedbackmicroservice.model.dto.FeedbackDto;
import org.example.softnifeedbackmicroservice.model.entity.Feedback;
import org.example.softnifeedbackmicroservice.repository.FeedbackRepository;
import org.example.softnifeedbackmicroservice.service.FeedbackService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FeedbackServiceImpl implements FeedbackService {
    private final ModelMapper modelMapper;
    private final FeedbackRepository feedbackRepository;

    public FeedbackServiceImpl(ModelMapper modelMapper, FeedbackRepository feedbackRepository) {
        this.modelMapper = modelMapper;
        this.feedbackRepository = feedbackRepository;
    }

    @Override
    public FeedbackDto saveFeedback(FeedbackDto feedbackDto) {
      Feedback feedback=  this.modelMapper.map(feedbackDto, Feedback.class);
      this.feedbackRepository.save(feedback);

      return feedbackDto;

    }

    @Override
    public void deleteFeedback(FeedbackDto contactUsDto) {
        Feedback feedback= this.modelMapper.map(contactUsDto, Feedback.class);
        this.feedbackRepository.delete(feedback);
    }

    @Override
    public void deleteFeedback(Long id) {
        this.feedbackRepository.deleteById(id);
    }

    @Override
    public List<FeedbackDto> getLastTen() {
        List<Feedback> feedbacks=this.feedbackRepository.findTop10ByOrderByIdAsc();
        List<FeedbackDto> feedbackDtos= new ArrayList<>();
        for (Feedback feedback : feedbacks) {
            FeedbackDto feedbackDto=this.modelMapper.map(feedback, FeedbackDto.class);
            feedbackDtos.add(feedbackDto);
        }

        return feedbackDtos;
    }
}
