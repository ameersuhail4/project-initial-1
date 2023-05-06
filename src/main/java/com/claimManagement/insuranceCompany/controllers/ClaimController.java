package com.claimManagement.insuranceCompany.controllers;

import com.claimManagement.insuranceCompany.DTO.ClaimDetailsDTO;
import com.claimManagement.insuranceCompany.DTO.PolicyDTO;
import com.claimManagement.insuranceCompany.DTO.SurveyorDTO;
import com.claimManagement.insuranceCompany.daoImp.ClaimDAOImp;
import com.claimManagement.insuranceCompany.daoImp.PolicyDAOImp;
import com.claimManagement.insuranceCompany.daoImp.SurveyorDAOImp;
import com.claimManagement.insuranceCompany.entities.ClaimDetails;
import com.claimManagement.insuranceCompany.exceptions.CustomException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ClaimController {

    @Autowired
    ClaimDAOImp claimDAOImp;
    @Autowired
    PolicyDAOImp policyDAOImp;
    @Autowired
    SurveyorDAOImp surveyorDAOImp;

    @GetMapping("/api/claims")
    List<ClaimDetailsDTO> getClaims() throws CustomException
    {
    	List<ClaimDetailsDTO> claimList=claimDAOImp.getClaims();
    	if(claimList.size()==0)
    	{
    		throw new CustomException("No Claims exist");
    	}
        return claimList;
    }

    @GetMapping("/api/claims/{id}")
    ClaimDetailsDTO getClaim(@PathVariable String id)
    {
        return claimDAOImp.getClaimById((id));
    }

    @PostMapping("/api/claims/new")

    ResponseEntity addNewClaim(@RequestBody ClaimDetailsDTO claimDetailsDTO) throws CustomException {
//        if (claimDetails == null) {
//            throw new IllegalArgumentException("Request body cannot be null");
//        }
        PolicyDTO policyDTO;
        SurveyorDTO surveyorDTO;

        policyDTO=policyDAOImp.getById(claimDetailsDTO.getPolicyNo());
        System.out.println("test"+policyDTO);

        int estimateLimit=claimDetailsDTO.getEstimatedLoss();

        surveyorDTO= surveyorDAOImp.getSurveyorByEstimateLimit(estimateLimit);
        claimDetailsDTO.setSurveyorId(surveyorDTO.getSurveyorId());

        ClaimDetails claimDetailsid=claimDAOImp.AddNewClaim(claimDetailsDTO);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body("added policy with id: "+claimDetailsid);
    }

    @PutMapping("/api/claims/{claimId}/update")
    String updateClaimById(@PathVariable String claimId,@RequestBody ClaimDetailsDTO claimDetailsDTO)
    {
        claimDAOImp.UpdateByClaimID(claimId,claimDetailsDTO);
        return "updated claim";
    }
}
