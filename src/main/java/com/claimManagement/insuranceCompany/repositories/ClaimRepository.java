package com.claimManagement.insuranceCompany.repositories;

import com.claimManagement.insuranceCompany.entities.ClaimDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public interface ClaimRepository extends JpaRepository<ClaimDetails,String> {

    ClaimDetails findByClaimId(String claimId);
    Boolean existsClaimDetailsByClaimId(String claimId);
}
