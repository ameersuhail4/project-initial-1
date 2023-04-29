package com.claimManagement.insuranceCompany.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.time.LocalDate;

@Data
@AllArgsConstructor
public class PolicyDTO {
    private String policyNo;
    private String insuredFirstName;
    private String insuredLastName;
    private LocalDate dateOfInsurance;
    private String emailId;
    private String vehicleNo;
    private boolean status;

    public PolicyDTO() {

    }
}
