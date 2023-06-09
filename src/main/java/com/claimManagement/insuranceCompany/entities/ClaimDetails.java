package com.claimManagement.insuranceCompany.entities;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDate;
import java.util.Objects;

@Entity
public class ClaimDetails {
    @Id
    @Column(name = "claimId",length = 10)
    private String claimId;

    @ManyToOne
    @JoinColumn(name = "policyNo",referencedColumnName = "policyNo")
    private Policy policy;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClaimDetails that = (ClaimDetails) o;
        return estimatedLoss == that.estimatedLoss && amtApprovedBySurveyor == that.amtApprovedBySurveyor && insuranceCompanyApproval == that.insuranceCompanyApproval && withdrawClaim == that.withdrawClaim && surveyorfees == that.surveyorfees && Objects.equals(claimId, that.claimId) && Objects.equals(policy, that.policy) && Objects.equals(dateOfAccident, that.dateOfAccident) && Objects.equals(claimStatus, that.claimStatus) && Objects.equals(surveyor, that.surveyor);
    }


    @Positive
    @NotNull
    private int estimatedLoss;

    @NotNull
    @PastOrPresent
    private LocalDate dateOfAccident;

    @NotNull

    @Pattern(regexp = "^(closed|open)$")
    @Column( name = "claimStatus",length = 30)
    private String claimStatus;

    @ManyToOne
    @JoinColumn( name = "surveyorId")
    private Surveyor surveyor;

    @NotNull
    @Positive
    private int amtApprovedBySurveyor;

    @NotNull
    private boolean insuranceCompanyApproval=false;

    @NotNull
    private boolean withdrawClaim=false;

    @NotNull
    private int surveyorfees;

    public ClaimDetails() {
    }

    public ClaimDetails (Policy policy, int estimatedLoss, LocalDate dateOfAccident, String claimStatus, Surveyor surveyor, int amtApprovedBySurveyor, boolean insuranceCompanyApproval, int surveyorfees) {
   
        this.policy = policy;
        this.estimatedLoss = estimatedLoss;
        this.dateOfAccident = dateOfAccident;
        this.claimStatus = claimStatus;
        this.surveyor = surveyor;
        this.amtApprovedBySurveyor = amtApprovedBySurveyor;
        this.insuranceCompanyApproval = insuranceCompanyApproval;
        this.surveyorfees = surveyorfees;
    }

    public String getClaimId() {
        return claimId;
    }

    public void setClaimId(String claimId) {
        this.claimId = claimId;
    }

    public Policy getPolicy() {
        return policy;
    }

    public void setPolicy(Policy policy) {
        this.policy = policy;
    }

    public int getEstimatedLoss() {
        return estimatedLoss;
    }

    public void setEstimatedLoss(int estimatedLoss) {
        this.estimatedLoss = estimatedLoss;
    }

    public LocalDate getDateOfAccident() {
        return dateOfAccident;
    }

    public void setDateOfAccident(LocalDate dateOfAccident) {
        this.dateOfAccident = dateOfAccident;
    }

    public String getClaimStatus() {
        return claimStatus;
    }

    public void setClaimStatus(String claimStatus) {
        this.claimStatus = claimStatus;
    }

    public Surveyor getSurveyor() {
        return surveyor;
    }

    public void setSurveyor(Surveyor surveyor) {
        this.surveyor = surveyor;
    }

    public int getAmtApprovedBySurveyor() {
        return amtApprovedBySurveyor;
    }

    public void setAmtApprovedBySurveyor(int amtApprovedBySurveyor) {
        this.amtApprovedBySurveyor = amtApprovedBySurveyor;
    }

    public boolean isInsuranceCompanyApproval() {
        return insuranceCompanyApproval;
    }

    public void setInsuranceCompanyApproval(boolean insuranceCompanyApproval) {
        this.insuranceCompanyApproval = insuranceCompanyApproval;
    }

    public boolean isWithdrawClaim() {
        return withdrawClaim;
    }

    public void setWithdrawClaim(boolean withdrawClaim) {
        this.withdrawClaim = withdrawClaim;
    }

    public int getSurveyorfees() {
        return surveyorfees;
    }

    public void setSurveyorfees(int surveyorfees) {
        this.surveyorfees = surveyorfees;
    }

    @Override
    public String toString() {
        return "ClaimDetails{" +
                "claimId='" + claimId + '\'' +
                ", policy=" + policy +
                ", estimatedLoss=" + estimatedLoss +
                ", dateOfAccident=" + dateOfAccident +
                ", claimStatus=" + claimStatus +
                ", surveyor=" + surveyor +
                ", amtApprovedBySurveyor=" + amtApprovedBySurveyor +
                ", insuranceCompanyApproval=" + insuranceCompanyApproval +
                ", withdrawClaim=" + withdrawClaim +
                ", surveyorfees=" + surveyorfees +
                '}';
    }


}
