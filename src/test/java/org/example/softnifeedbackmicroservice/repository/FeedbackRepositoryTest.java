package org.example.softnifeedbackmicroservice.repository;

import org.example.softnifeedbackmicroservice.model.entity.Feedback;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestDatabase
@ActiveProfiles("test")
class FeedbackRepositoryTest {
    @Autowired
    private FeedbackRepository feedbackRepository;

    @Test
    public void testFindTop10ByOrderByIdAsc() {

        for (int i = 1; i <= 15; i++) {
            Feedback feedback = new Feedback();
            feedbackRepository.save(feedback);
        }
        List<Feedback> feedbacks = feedbackRepository.findTop10ByOrderByIdAsc();
        assertNotNull(feedbacks);
        assertEquals(10, feedbacks.size());

    }
}