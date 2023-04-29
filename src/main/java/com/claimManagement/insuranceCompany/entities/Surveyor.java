package com.claimManagement.insuranceCompany.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.List;

@Entity
public class Surveyor {
    @Id
    private long surveyorId;

    @NotNull
    private String firstName;

    @NotNull
    private String lastName;

    @NotNull
    @Positive
    private int estimateLimit;

    @OneToMany(mappedBy = "surveyor")
    private List<ClaimDetails> ClaimDetails;
    public Surveyor() {
    }

    public Surveyor(long surveyorId, String firstName, String lastName, int estimateLimit) {
        this.surveyorId = surveyorId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.estimateLimit = estimateLimit;
    }

    public long getSurveyorId() {
        return surveyorId;
    }

    public void setSurveyorId(long surveyorId) {
        this.surveyorId = surveyorId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getEstimateLimit() {
        return estimateLimit;
    }

    public void setEstimateLimit(int estimateLimit) {
        this.estimateLimit = estimateLimit;
    }

    @Override
    public String toString() {
        return "Surveyor{" +
                "surveyorId=" + surveyorId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", estimateLimit=" + estimateLimit +
                '}';
    }
}
