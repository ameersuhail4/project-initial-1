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


}
