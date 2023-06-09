package com.claimManagement.insuranceCompany.serviceImp;

import com.claimManagement.insuranceCompany.DTO.PolicyDTO;
import com.claimManagement.insuranceCompany.entities.Policy;
import com.claimManagement.insuranceCompany.exceptions.CustomException;
import com.claimManagement.insuranceCompany.repositories.PolicyRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PolicyDAOImpTest {

    @Mock
    PolicyRepository policyRepository;

    @InjectMocks
    PolicyServiceImp policyServiceImp;

    Policy policy;
    List<Policy> policyList=new ArrayList<>();
    @BeforeEach
    void setup()
    {
        policy=new Policy("su44623","shaik","suhail", LocalDate.parse("2023-05-04"),"ameersuhail3741@gmail.com","AP02446",true);
    }
    @Test
    void addPolicy() {
        PolicyDTO policyDTO= PolicyServiceImp.toDto(policy);
//        System.out.println("1dto:"+policyDTO.hashCode());
//        System.out.println("2actal:"+policy.hashCode());
        when(policyRepository.save(policy)).thenReturn(policy);
        Policy policy1= policyServiceImp.addPolicy(policyDTO);
        assertNotNull(policy1);
    }

    @Test
    void getById() throws CustomException {
       // when(policyRepository.existsPolicyByPolicyNo("su44623")).thenReturn(true);
        when(policyRepository.findById("su44623")).thenReturn(Optional.of(policy));
        PolicyDTO policy1= policyServiceImp.getById("su44623");
        assertNotNull(policy1);

    }

    @Test
    void getAllPolicies() throws CustomException {
        policyList.add(policy);
        when(policyRepository.findAll()).thenReturn(policyList);
        List<PolicyDTO> policies= policyServiceImp.getAllPolicies();
        assertNotNull(policies);
    }
}