package com.claimManagement.insuranceCompany.service;

import com.claimManagement.insuranceCompany.DTO.SurveyorDTO;
import com.claimManagement.insuranceCompany.entities.Surveyor;
import com.claimManagement.insuranceCompany.exceptions.CustomException;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface SurveyorService {


    Surveyor addSurveyor(SurveyorDTO surveyorDTO);
    List<SurveyorDTO> listOfSurveyors() throws CustomException;
    SurveyorDTO getSurveyorByEstimateLimit(int estimatelimit) throws CustomException;
    SurveyorDTO getSurveyorById(int id) throws CustomException;
    String addSurveyorByHardCode(); 
}
