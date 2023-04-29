package com.claimManagement.insuranceCompany.entities;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.List;

@Entity
public class Policy {
    @Id
    @Column(name = "policyNo",
            length = 7
            )
    private String policyNo;

    @NotNull

    @Size(min = 5,message = "must be minimum 5 characters")
    private String insuredFirstName;

    @NotNull
    @Size(min = 5,message = "must be minimum 5 characters")
    private String insuredLastName;

    @NotNull
    @PastOrPresent(message = "must not be earlier than current date")
    private LocalDate dateOfInsurance;

    @NotNull
    private String emailId;

    @NotNull
    private String vehicleNo;

    @NotNull
    private boolean status;
    @OneToMany(mappedBy = "policy")
    private List<ClaimDetails> claimDetails;
    public Policy() {
    }

    public Policy(
                  String insuredFirstName,
                  String insuredLastName,
                  LocalDate dateOfInsurance,
                  String emailId,
                  String vehicleNo,
                  boolean status) {

        this.insuredFirstName = insuredFirstName;
        this.insuredLastName = insuredLastName;
        this.dateOfInsurance = dateOfInsurance;
        this.emailId = emailId;
        this.vehicleNo = vehicleNo;
        this.status = status;
    }

    public String getPolicyNo() {
        return policyNo;
    }

    public void setPolicyNo(String policyNo) {
        this.policyNo = policyNo;
    }

    public String getInsuredFirstName() {
        return insuredFirstName;
    }

    public void setInsuredFirstName(String insuredFirstName) {
        this.insuredFirstName = insuredFirstName;
    }

    public String getInsuredLastName() {
        return insuredLastName;
    }

    public void setInsuredLastName(String insuredLastName) {
        this.insuredLastName = insuredLastName;
    }

    public LocalDate getDateOfInsurance() {
        return dateOfInsurance;
    }

    public void setDateOfInsurance(LocalDate dateOfInsurance) {
        this.dateOfInsurance = dateOfInsurance;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getVehicleNo() {
        return vehicleNo;
    }

    public void setVehicleNo(String vehicleNo) {
        this.vehicleNo = vehicleNo;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Policy{" +
                "policyNo='" + policyNo + '\'' +
                ", insuredFirstName='" + insuredFirstName + '\'' +
                ", insuredLastName='" + insuredLastName + '\'' +
                ", dateOfInsurance=" + dateOfInsurance +
                ", emailId='" + emailId + '\'' +
                ", vehicleNo='" + vehicleNo + '\'' +
                ", status=" + status +
                '}';
    }
}
