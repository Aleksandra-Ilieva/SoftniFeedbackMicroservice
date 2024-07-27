package org.example.softnifeedbackmicroservice.controller;

import org.example.softnifeedbackmicroservice.model.dto.FeedbackDto;
import org.example.softnifeedbackmicroservice.model.entity.Feedback;
import org.example.softnifeedbackmicroservice.repository.FeedbackRepository;
import org.example.softnifeedbackmicroservice.service.FeedbackService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestDatabase
@ActiveProfiles("test")
class FeedbackControllerTest {

    @Autowired
    private FeedbackRepository feedbackRepository;

    @Autowired
    private MockMvc mockMvc;

    private final String validApiKey = "test";

    @BeforeEach
    public void setUp() {
        feedbackRepository.deleteAll();
        this.feedbackRepository.deleteAll();
        Feedback feedback = new Feedback();
        feedback.setEmail("A@test.com");
        feedback.setMessage("Great service!");
        feedbackRepository.save(feedback);

        Feedback feedback2 = new Feedback();
        feedback2.setEmail("A@test.com");
        feedback2.setMessage("Needs improvement.");
        feedbackRepository.save(feedback2);
    }

    @Test
    public void testSaveFeedback_Success() throws Exception {





        mockMvc.perform(post("/api/v1/feedback")
                        .param("api_key", validApiKey)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"message\":\"Great service!\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Great service!"));
    }

    @Test
    public void testSaveFeedback_Unauthorized() throws Exception {
        mockMvc.perform(post("/api/v1/feedback")
                        .param("api_key", "invalid-api-key")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"message\":\"Great service!\"}"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    public void testDeleteFeedback_Success() throws Exception {


        mockMvc.perform(delete("/api/v1/feedback/1")
                        .param("api_key", validApiKey))
                .andExpect(status().isNoContent());
    }

    @Test
    public void testDeleteFeedback_Unauthorized() throws Exception {
        mockMvc.perform(delete("/api/v1/feedback/1")
                        .param("api_key", "invalid-api-key"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    public void testGetAllFeedbacks_Success() throws Exception {

        mockMvc.perform(get("/api/v1/feedback")
                        .param("api_key", validApiKey))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].message").value("Great service!"))
                .andExpect(jsonPath("$[1].message").value("Needs improvement."));
    }

    @Test
    public void testGetAllFeedbacks_Unauthorized() throws Exception {
        mockMvc.perform(get("/api/v1/feedback")
                        .param("api_key", "invalid-api-key"))
                .andExpect(status().isUnauthorized());
    }

}