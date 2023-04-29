package com.claimManagement.insuranceCompany.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor

public class SurveyorDTO {
    private long surveyorId;
    private String firstName;
    private String lastName;
    private int estimateLimit;

    public SurveyorDTO() {

    }
}
