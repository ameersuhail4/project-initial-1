package com.claimManagement.insuranceCompany.controllers;

import java.util.List;

import com.claimManagement.insuranceCompany.DTO.PolicyDTO;
import com.claimManagement.insuranceCompany.exceptions.CustomException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.claimManagement.insuranceCompany.serviceImp.PolicyServiceImp;
import com.claimManagement.insuranceCompany.entities.Policy;
@CrossOrigin(origins = "http://localhost:4200")

@RestController
public class PolicyController {
	
	@Autowired
	PolicyServiceImp policyServiceImp;

	@GetMapping(value = "/api/policies")
	public List<PolicyDTO> get() throws CustomException {

		return policyServiceImp.getAllPolicies();
	}
	@GetMapping(value = "/api/policies/id/{id}")
	public PolicyDTO get(@PathVariable String id) throws CustomException {

		return policyServiceImp.getById(id);
	}

	@PostMapping("/api/policies/new")
	public String add(@RequestBody PolicyDTO po)
	{
		Policy policy= policyServiceImp.addPolicy(po);
		return "policy added with id:"+policy.getPolicyNo();
	}
}
