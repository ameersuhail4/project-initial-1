package com.claimManagement.insuranceCompany.daoImp;

import com.claimManagement.insuranceCompany.DTO.PolicyDTO;
import com.claimManagement.insuranceCompany.DAO.PolicyDAO;
import com.claimManagement.insuranceCompany.entities.Policy;
import com.claimManagement.insuranceCompany.exceptions.CustomException;
import com.claimManagement.insuranceCompany.repositories.PolicyRepository;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;


@Service

public class PolicyDAOImp implements PolicyDAO {

   //injection of PolicyRepository
    @Autowired
     PolicyRepository policyRepository;

    //inserting into policy
    @Override
    public String addPolicy(PolicyDTO policyDTO) {
        Policy policyEntity=toEntity(policyDTO);
        policyEntity.setPolicyNo(generatePolicyNo(policyEntity));
        policyRepository.save(policyEntity);
        return "added";
    }

    @Override
    public PolicyDTO getById(String policyNo) throws CustomException {
       if(policyRepository.existsPolicyByPolicyNo(policyNo))
       {
           Policy policy = policyRepository.findByPolicyNo(policyNo);
           PolicyDTO policyDTO=toDto(policy);
           return policyDTO;
       }
       else
       {
           throw new CustomException("No policy with id:"+policyNo);
       }
    }

    @Override
    public List<PolicyDTO> getAllPolicies() {

//        return  policyRepository.findAllById()
        List<Policy> it= policyRepository.findAll();
        List<PolicyDTO> policyDTOS=it.stream().map((x)->toDto(x)).collect(Collectors.toList());
        return policyDTOS;
    }

    //generating policyNo
    static   String generatePolicyNo(Policy p)
    {
        String ln = p.getInsuredLastName();
        String vn = p.getVehicleNo();
        String year = String.valueOf(p.getDateOfInsurance().getYear());
        return ln.substring(0,2) +
                vn.substring(vn.length() - 3) +
                year.substring(year.length() - 2);
    }

	@Override
	public String AddPolicyByHardCode() {
		Policy p=new Policy( "Shaik", "Ameer",LocalDate.of(2022,1,1),"ameersuhail@gmail.com", "ABC123",true);
		p.setPolicyNo(generatePolicyNo(p));
		policyRepository.save(p);
		return "data hard coded to policy database";
	}
    public static PolicyDTO toDto(Policy policy) {
        PolicyDTO policyDto = new PolicyDTO();
        policyDto.setPolicyNo(policy.getPolicyNo());
        policyDto.setInsuredFirstName(policy.getInsuredFirstName());
        policyDto.setInsuredLastName(policy.getInsuredLastName());
        policyDto.setDateOfInsurance(policy.getDateOfInsurance());
        policyDto.setEmailId(policy.getEmailId());
        policyDto.setVehicleNo(policy.getVehicleNo());
        policyDto.setStatus(policy.isStatus());
        return policyDto;
    }

    public static Policy toEntity(PolicyDTO policyDto) {
        Policy policy = new Policy();
        policy.setPolicyNo(policyDto.getPolicyNo());
        policy.setInsuredFirstName(policyDto.getInsuredFirstName());
        policy.setInsuredLastName(policyDto.getInsuredLastName());
        policy.setDateOfInsurance(policyDto.getDateOfInsurance());
        policy.setEmailId(policyDto.getEmailId());
        policy.setVehicleNo(policyDto.getVehicleNo());
        policy.setStatus(policyDto.isStatus());
        return policy;
    }
}
