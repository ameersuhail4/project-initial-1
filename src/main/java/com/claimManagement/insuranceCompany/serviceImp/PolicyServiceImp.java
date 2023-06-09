package com.claimManagement.insuranceCompany.serviceImp;

import com.claimManagement.insuranceCompany.DTO.PolicyDTO;
import com.claimManagement.insuranceCompany.service.PolicyService;
import com.claimManagement.insuranceCompany.entities.Policy;
import com.claimManagement.insuranceCompany.exceptions.CustomException;
import com.claimManagement.insuranceCompany.repositories.PolicyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service

public class PolicyServiceImp implements PolicyService {

   //injection of PolicyRepository
    @Autowired
     PolicyRepository policyRepository;

    //inserting into policy
    @Override
    public Policy addPolicy(PolicyDTO policyDTO) {
        Policy policyEntity=toEntity(policyDTO);
        policyEntity.setPolicyNo(generatePolicyNo(policyEntity));
        return policyRepository.save(policyEntity);
    }

    //Retrieve Policy by PolicyNo.
    @Override
    public PolicyDTO getById(String policyNo) throws CustomException {
        //throws exception if no policy found with PolicyNo.
        Optional<Policy> policy = Optional.of(policyRepository.findById(policyNo).orElseThrow(() -> new CustomException("No Policy found with id: "+policyNo)));
        return toDto(policy.get());
    }

    //Retrieve list of policies.
    @Override
    public List<PolicyDTO> getAllPolicies() throws CustomException {

        List<Policy> policyList= policyRepository.findAll();
        if (policyList.isEmpty())
        {
            throw new CustomException("No policies Exist!");
        }
        return policyList.stream().map((x)->toDto(x)).collect(Collectors.toList());
    }

    //generating policyNo based on lastname,vehicle number and year.
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
		Policy p=new Policy("Am12322", "Shaik", "Ameer",LocalDate.of(2022,1,1),"ameersuhail@gmail.com", "ABC123",true);
		p.setPolicyNo(generatePolicyNo(p));
		policyRepository.save(p);
		return "data hard coded to policy database";
	}

    //Entity to DTO.
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

    //DTO to entity.
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
