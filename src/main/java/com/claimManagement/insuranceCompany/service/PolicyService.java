package com.claimManagement.insuranceCompany.service;

import com.claimManagement.insuranceCompany.DTO.PolicyDTO;
import com.claimManagement.insuranceCompany.entities.Policy;
import com.claimManagement.insuranceCompany.exceptions.CustomException;

import java.util.List;

public interface PolicyService {


	Policy addPolicy(PolicyDTO p);

    PolicyDTO getById(String policyNo) throws CustomException;

    List<PolicyDTO> getAllPolicies() throws CustomException;
    
    String AddPolicyByHardCode();

}
