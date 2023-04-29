package com.claimManagement.insuranceCompany.DAO;

import com.claimManagement.insuranceCompany.DTO.ClaimDetailsDTO;


import java.util.List;

public interface ClaimDAO {
    List<ClaimDetailsDTO> getClaims();
    String AddNewClaim(ClaimDetailsDTO claimDetailsDTO);
    String UpdateByClaimID(String claimId,ClaimDetailsDTO claimDetailsDTO);
    ClaimDetailsDTO getClaimById(String id);
}
