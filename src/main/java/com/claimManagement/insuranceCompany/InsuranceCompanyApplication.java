package com.claimManagement.insuranceCompany;

import com.claimManagement.insuranceCompany.daoImp.PolicyDAOImp;
import com.claimManagement.insuranceCompany.daoImp.SurveyorDAOImp;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
//@EnableEurekaClient
public class InsuranceCompanyApplication implements CommandLineRunner{
	@Autowired
	PolicyDAOImp pdaoImp;
	@Autowired
	SurveyorDAOImp sdaoImp;
	public static void main(String[] args) {
		SpringApplication.run(InsuranceCompanyApplication.class, args);
		
	}
	@Override
	public void run(String... args) throws Exception {
		
//		pdaoImp.AddPolicyByHardCode();
//		sdaoImp.addSurveyorByHardCode();
	}
	
}
