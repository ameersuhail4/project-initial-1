package com.claimManagement.insuranceCompany.DAO;

import com.claimManagement.insuranceCompany.DTO.PolicyDTO;
import com.claimManagement.insuranceCompany.entities.Policy;
import com.claimManagement.insuranceCompany.exceptions.CustomException;

import java.util.List;

public interface PolicyDAO {


	Policy addPolicy(PolicyDTO p);

    PolicyDTO getById(String policyNo) throws CustomException;

    List<PolicyDTO> getAllPolicies();
    
    String AddPolicyByHardCode();

}
