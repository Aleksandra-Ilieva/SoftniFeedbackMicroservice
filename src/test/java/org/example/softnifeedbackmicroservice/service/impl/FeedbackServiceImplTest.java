package org.example.softnifeedbackmicroservice.service.impl;

import org.example.softnifeedbackmicroservice.model.dto.FeedbackDto;
import org.example.softnifeedbackmicroservice.model.entity.Feedback;
import org.example.softnifeedbackmicroservice.repository.FeedbackRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class FeedbackServiceImplTest {
    @Mock
    private FeedbackRepository feedbackRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private FeedbackServiceImpl feedbackService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void saveFeedback_shouldSaveAndReturnFeedbackDto() {
        FeedbackDto feedbackDto = new FeedbackDto();
        Feedback feedback = new Feedback();
        when(modelMapper.map(feedbackDto, Feedback.class)).thenReturn(feedback);
        when(feedbackRepository.save(feedback)).thenReturn(feedback);
        when(modelMapper.map(feedback, FeedbackDto.class)).thenReturn(feedbackDto);

        FeedbackDto savedFeedbackDto = feedbackService.saveFeedback(feedbackDto);

        verify(feedbackRepository, times(1)).save(feedback);
        assertNotNull(savedFeedbackDto);
        assertEquals(feedbackDto, savedFeedbackDto);
    }

    @Test
    void deleteFeedback_shouldDeleteFeedback() {
        FeedbackDto feedbackDto = new FeedbackDto();
        Feedback feedback = new Feedback();
        when(modelMapper.map(feedbackDto, Feedback.class)).thenReturn(feedback);

        feedbackService.deleteFeedback(feedbackDto);

        verify(feedbackRepository, times(1)).delete(feedback);
    }

    @Test
    void deleteFeedbackById_shouldDeleteFeedbackById() {
        Long id = 1L;

        feedbackService.deleteFeedback(id);

        verify(feedbackRepository, times(1)).deleteById(id);
    }

    @Test
    void getLastTen_shouldReturnLastTenFeedbacks() {
        Feedback feedback1 = new Feedback();
        Feedback feedback2 = new Feedback();
        FeedbackDto feedbackDto1 = new FeedbackDto();
        FeedbackDto feedbackDto2 = new FeedbackDto();

        when(feedbackRepository.findTop10ByOrderByIdAsc()).thenReturn(Arrays.asList(feedback1, feedback2));
        when(modelMapper.map(feedback1, FeedbackDto.class)).thenReturn(feedbackDto1);
        when(modelMapper.map(feedback2, FeedbackDto.class)).thenReturn(feedbackDto2);

        List<FeedbackDto> feedbackDtos = feedbackService.getLastTen();

        assertNotNull(feedbackDtos);
        assertEquals(2, feedbackDtos.size());
        assertTrue(feedbackDtos.contains(feedbackDto1));
        assertTrue(feedbackDtos.contains(feedbackDto2));
    }
}

