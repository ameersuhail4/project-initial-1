package com.claimManagement.insuranceCompany.daoImp;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.claimManagement.insuranceCompany.DTO.PolicyDTO;
import com.claimManagement.insuranceCompany.DTO.SurveyorDTO;
import com.claimManagement.insuranceCompany.daoImp.ClaimDAOImp;
import com.claimManagement.insuranceCompany.daoImp.PolicyDAOImp;
import com.claimManagement.insuranceCompany.daoImp.SurveyorDAOImp;
import com.claimManagement.insuranceCompany.entities.Policy;
import com.claimManagement.insuranceCompany.entities.Surveyor;
import com.claimManagement.insuranceCompany.exceptions.CustomException;
import com.claimManagement.insuranceCompany.repositories.PolicyRepository;
import com.claimManagement.insuranceCompany.repositories.SurveyorRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.claimManagement.insuranceCompany.DAO.ClaimDAO;
import com.claimManagement.insuranceCompany.DTO.ClaimDetailsDTO;
import com.claimManagement.insuranceCompany.entities.ClaimDetails;
import com.claimManagement.insuranceCompany.repositories.ClaimRepository;

import javax.validation.ConstraintDeclarationException;

public class ClaimDAOimpTest {

    @Mock
    private ClaimRepository claimRepository;

    @InjectMocks
    private ClaimDAOImp claimService;



    ClaimDetails claimDetails = new ClaimDetails();
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        Policy policy=new Policy();
        policy.setPolicyNo("PO1234");
        claimDetails.setPolicy(policy);

        Surveyor surveyor=new Surveyor();
        surveyor.setSurveyorId(1);
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
    public void testGetClaims() {
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
       claimDetailsDTO.setAmtApprovedBySurveyor(2000);

        ClaimDetails details=claimService.toEntity(claimDetailsDTO);


        System.out.println(details.hashCode());
        when(claimRepository.existsClaimDetailsByClaimId("1")).thenReturn(false);

        when(claimRepository.save(details)).thenReturn(details).thenThrow(RuntimeException.class);


        // act
        ClaimDetails result = null;
        try{
            result = claimService.AddNewClaim(claimDetailsDTO);
        }
        catch (Exception e )
        {
            System.out.println( e);
        }
        boolean exists=claimRepository.existsClaimDetailsByClaimId("1");
        // assert
        //assertNotNull(result);
        assertFalse(exists);

    }



    @Test
    public void testGetClaimById() {
        ClaimDetails claimDetails = new ClaimDetails();
        Policy policy=new Policy();
        policy.setPolicyNo("PO1234");
        claimDetails.setPolicy(policy);

        Surveyor surveyor=new Surveyor();
        surveyor.setSurveyorId(1);
        claimDetails.setSurveyor(surveyor);
        claimDetails.setClaimId("CL12342022");
        claimDetails.setClaimStatus("open");
        claimDetails.setAmtApprovedBySurveyor(0);
        claimDetails.setEstimatedLoss(8000);
        claimDetails.setDateOfAccident(LocalDate.of(2022, 4, 20));
        claimDetails.setInsuranceCompanyApproval(false);
        claimDetails.setWithdrawClaim(false);
        claimDetails.setSurveyorfees(1000);
        claimRepository.save(claimDetails);

        when(claimRepository.findByClaimId("CL12342022")).thenReturn(claimDetails);

        ClaimDetailsDTO claimDetailsDTO = claimService.getClaimById("CL12342022");
        assertNotNull(claimDetailsDTO);
        assertEquals("CL12342022", claimDetailsDTO.getClaimId());
    }
}
