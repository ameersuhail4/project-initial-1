package com.claimManagement.insuranceCompany.DTO;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClaimDetailsDTO {
    private String claimId;
    private String policyNo;
    private int estimatedLoss;
    private LocalDate dateOfAccident;
    private String claimStatus;
    private long surveyorId;
    private int amtApprovedBySurveyor;
    private boolean insuranceCompanyApproval=false;
    private boolean withdrawClaim=false;
    private int surveyorfees;

    public String getClaimId() {
        return claimId;
    }

    public void setClaimId(String claimId) {
        this.claimId = claimId;
    }

    public String getPolicyNo() {
        return policyNo;
    }

    public void setPolicyNo(String policyNo) {
        this.policyNo = policyNo;
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

    public long getSurveyorId() {
        return surveyorId;
    }

    public void setSurveyorId(long surveyorId) {
        this.surveyorId = surveyorId;
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
}
