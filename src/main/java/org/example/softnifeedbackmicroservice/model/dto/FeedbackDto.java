package org.example.softnifeedbackmicroservice.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class FeedbackDto {

    private long id;

    @NotNull
    @NotBlank(message = "Full Name must not be blank!")
    private String fullName;

    @NotNull
    @NotBlank(message = "Email must not be blank!")
    private String email;

    @NotNull
    @NotBlank(message = "Message must not be blank!")
    private String message;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
