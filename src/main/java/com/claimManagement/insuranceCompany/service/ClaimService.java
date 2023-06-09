package com.claimManagement.insuranceCompany.service;

import com.claimManagement.insuranceCompany.DTO.ClaimDetailsDTO;
import com.claimManagement.insuranceCompany.entities.ClaimDetails;
import com.claimManagement.insuranceCompany.exceptions.CustomException;


import java.util.List;

public interface ClaimService {
    List<ClaimDetailsDTO> getClaims() throws CustomException;
    ClaimDetails AddNewClaim(ClaimDetailsDTO claimDetailsDTO) throws CustomException;
    ClaimDetails UpdateByClaimID(String claimId,ClaimDetailsDTO claimDetailsDTO) throws CustomException;
    ClaimDetailsDTO getClaimById(String id) throws CustomException;
}

