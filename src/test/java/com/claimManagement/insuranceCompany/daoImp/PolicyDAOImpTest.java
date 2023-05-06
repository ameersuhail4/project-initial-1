package com.claimManagement.insuranceCompany.daoImp;

import com.claimManagement.insuranceCompany.DTO.PolicyDTO;
import com.claimManagement.insuranceCompany.entities.Policy;
import com.claimManagement.insuranceCompany.repositories.PolicyRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PolicyDAOImpTest {

    @Mock
    PolicyRepository policyRepository;

    @InjectMocks
    PolicyDAOImp policyDAOImp;

    Policy policy;
    @BeforeEach
    void setup()
    {
        policy=new Policy("su44623","shaik","suhail", LocalDate.parse("2023-05-04"),"ameersuhail3741@gmail.com","AP02446",true);
    }
    @Test
    void addPolicy() {
        PolicyDTO policyDTO=PolicyDAOImp.toDto(policy);
        System.out.println("1dto:"+policyDTO.hashCode());
        System.out.println("2actal:"+policy.hashCode());
        when(policyRepository.save(policy)).thenReturn(policy);
        Policy policy1=policyDAOImp.addPolicy(policyDTO);
        assertNotNull(policy1);
    }

    @Test
    void getById() {
        policyRepository.save(policy);
        when(policyRepository.findByPolicyNo("su44623")).thenReturn(policy);


    }

    @Test
    void getAllPolicies() {
    }
}