package com.claimManagement.insuranceCompany.repositories;

import com.claimManagement.insuranceCompany.entities.Policy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public interface PolicyRepository extends JpaRepository<Policy,String> {
	
		
	
        Boolean existsPolicyByPolicyNo(String policyNo);
        
        Policy findByPolicyNo(String policyNo);
}
