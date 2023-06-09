package com.claimManagement.insuranceCompany.controllers;

import com.claimManagement.insuranceCompany.DTO.ClaimDetailsDTO;
import com.claimManagement.insuranceCompany.serviceImp.ClaimServiceImp;
import com.claimManagement.insuranceCompany.serviceImp.PolicyServiceImp;
import com.claimManagement.insuranceCompany.serviceImp.SurveyorServiceImp;
import com.claimManagement.insuranceCompany.entities.ClaimDetails;
import com.claimManagement.insuranceCompany.exceptions.CustomException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class ClaimController implements ErrorController{

    @Autowired
    ClaimServiceImp claimServiceImp;
    @Autowired
    PolicyServiceImp policyServiceImp;
    @Autowired
    SurveyorServiceImp surveyorServiceImp;



    //API to retrieve all claims
    @GetMapping("/api/claims")
    ResponseEntity<List<ClaimDetailsDTO>> getClaims() throws CustomException
    {
    	List<ClaimDetailsDTO> claimList= claimServiceImp.getClaims();
        return ResponseEntity.status(HttpStatus.OK).body(claimList);
    }

    //API to retrieve claim by id.
    @GetMapping("/api/claims/id/{id}")
    ResponseEntity<ClaimDetailsDTO> getClaim(@PathVariable String id) throws CustomException {
        return ResponseEntity.status(HttpStatus.OK).body(claimServiceImp.getClaimById((id)));
    }

    //API to post new claim.
    @PostMapping("/api/claims/new")
    ResponseEntity<String> addNewClaim(@RequestBody ClaimDetailsDTO claimDetailsDTO) throws CustomException {
        System.out.println("body req:"+claimDetailsDTO);
        ClaimDetails claimDetailsid= claimServiceImp.AddNewClaim(claimDetailsDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    //API to update Claim by id
    @PutMapping("/api/claims/{claimId}/update")
    ResponseEntity<String> updateClaimById(@PathVariable String claimId,@RequestBody ClaimDetailsDTO claimDetailsDTO) throws CustomException {
        System.out.println("res:"+claimDetailsDTO);
        ClaimDetails claimDetails=claimServiceImp.UpdateByClaimID(claimId,claimDetailsDTO);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
    @RequestMapping("/error")
    public ResponseEntity<String> handleError() {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Custom 404 Not Found Response");
    }




}
