package com.claimManagement.insuranceCompany;

import com.claimManagement.insuranceCompany.serviceImp.PolicyServiceImp;
import com.claimManagement.insuranceCompany.serviceImp.SurveyorServiceImp;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
//import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class InsuranceCompanyApplication implements CommandLineRunner{
	@Autowired
	PolicyServiceImp pdaoImp;
	@Autowired
	SurveyorServiceImp sdaoImp;
	public static void main(String[] args) {
		SpringApplication.run(InsuranceCompanyApplication.class, args);
		
	}
	@Override
	public void run(String... args) throws Exception {
		
//		pdaoImp.AddPolicyByHardCode();
//		sdaoImp.addSurveyorByHardCode();
	}
	
}
