package com.claimManagement.insuranceCompany.serviceImp;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.claimManagement.insuranceCompany.DTO.PolicyDTO;
import com.claimManagement.insuranceCompany.DTO.SurveyorDTO;
import com.claimManagement.insuranceCompany.entities.Policy;
import com.claimManagement.insuranceCompany.entities.Surveyor;
import com.claimManagement.insuranceCompany.exceptions.CustomException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.claimManagement.insuranceCompany.DTO.ClaimDetailsDTO;
import com.claimManagement.insuranceCompany.entities.ClaimDetails;
import com.claimManagement.insuranceCompany.repositories.ClaimRepository;


public class ClaimDAOimpTest {

    @Mock
    private ClaimRepository claimRepository;

    @InjectMocks
    private ClaimServiceImp claimService;

    @Mock
    PolicyServiceImp policyServiceImp;
    @Mock
    SurveyorServiceImp surveyorServiceImp;
    ClaimDetails claimDetails ;
    Policy policy;
    Surveyor surveyor;
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        claimDetails= new ClaimDetails();
        policy=new Policy();
        policy.setPolicyNo("PO12356");
        claimDetails.setPolicy(policy);

        surveyor=new Surveyor();
        surveyor.setSurveyorId(1);
        surveyor.setEstimateLimit(10000);
        surveyor.setFirstName("Ameer");
        surveyor.setLastName("Shaik");

        claimDetails.setSurveyor(surveyor);
        claimDetails.setClaimId("CL12342022");
        claimDetails.setClaimStatus("open");
        claimDetails.setAmtApprovedBySurveyor(0);
        claimDetails.setEstimatedLoss(8000);
        claimDetails.setDateOfAccident(LocalDate.of(2022, 4, 20));
        claimDetails.setInsuranceCompanyApproval(false);
        claimDetails.setWithdrawClaim(false);
        claimDetails.setSurveyorfees(1000);

    }


    @Test
    public void testGetClaims() throws CustomException {
        List<ClaimDetails> claimDetailsList = new ArrayList<>();

        claimDetailsList.add(claimDetails);

        when(claimRepository.findAll()).thenReturn(claimDetailsList);

        List<ClaimDetailsDTO> claimDetailsDTOList = claimService.getClaims();
        assertEquals(1, claimDetailsDTOList.size());
    }

    @Test
    public void testAddNewClaim() throws CustomException {
        // arrange
        ClaimDetailsDTO claimDetailsDTO = new ClaimDetailsDTO();
        claimDetailsDTO.setPolicyNo("PO12356");
        claimDetailsDTO.setEstimatedLoss(10000);
        claimDetailsDTO.setDateOfAccident(LocalDate.parse("2023-05-04"));

        claimDetailsDTO.setSurveyorId(1);
        claimDetailsDTO.setAmtApprovedBySurveyor(8000);

        ClaimDetails details=claimService.toEntity(claimDetailsDTO);
        PolicyDTO policyDTO = new PolicyDTO();
        policyDTO.setPolicyNo("PO12356");
        policyDTO.setInsuredFirstName("John");
        policyDTO.setInsuredLastName("Doe");
        policyDTO.setDateOfInsurance(LocalDate.of(2022,5,4));
        policyDTO.setVehicleNo("AP012356");
        policyDTO.setStatus(true);

        SurveyorDTO surveyorDTO = new SurveyorDTO();
        surveyorDTO.setSurveyorId(1);
        surveyorDTO.setFirstName("Ameer");
        surveyorDTO.setLastName("Shaik");
        surveyorDTO.setEstimateLimit(10000);

        Policy policy1= PolicyServiceImp.toEntity(policyDTO);
        details.setSurveyor(surveyor);
        details.setPolicy(policy1);
        details.setClaimId("CL23562023");
        details.setClaimStatus("open");
        details.setSurveyorfees(2000);
        when(claimRepository.existsClaimDetailsByClaimId("CL23562023")).thenReturn(false);
        //when(policyDAOImp.getById(anyString())).thenReturn(policyDTO);
        when(policyServiceImp.getById("PO12356")).thenReturn(policyDTO);

        when(surveyorServiceImp.getSurveyorByEstimateLimit(10000)).thenReturn(surveyorDTO);
        when(claimRepository.save(details)).thenReturn(details);
        //System.out.println(details);

        // act
        ClaimDetails result = claimService.AddNewClaim(claimDetailsDTO);
        //System.out.println(result);
        boolean exists=claimRepository.existsClaimDetailsByClaimId("CL23562023");
        // assert
        assertNotNull(result);
        assertFalse(exists);

    }
    @Test
    public void testGetClaimById() throws CustomException {


        when(claimRepository.findById("CL12342022")).thenReturn(Optional.of(claimDetails));

        ClaimDetailsDTO claimDetailsDTO = claimService.getClaimById("CL12342022");
        assertNotNull(claimDetailsDTO);
        assertEquals("CL12342022", claimDetailsDTO.getClaimId());
    }
}
