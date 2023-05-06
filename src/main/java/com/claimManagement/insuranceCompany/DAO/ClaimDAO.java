package com.claimManagement.insuranceCompany.DAO;

import com.claimManagement.insuranceCompany.DTO.ClaimDetailsDTO;
import com.claimManagement.insuranceCompany.entities.ClaimDetails;
import com.claimManagement.insuranceCompany.exceptions.CustomException;


import java.util.List;

public interface ClaimDAO {
    List<ClaimDetailsDTO> getClaims();
    ClaimDetails AddNewClaim(ClaimDetailsDTO claimDetailsDTO) throws CustomException;
    ClaimDetails UpdateByClaimID(String claimId,ClaimDetailsDTO claimDetailsDTO);
    ClaimDetailsDTO getClaimById(String id);
}

