package com.claimManagement.insuranceCompany.DAO;

import com.claimManagement.insuranceCompany.DTO.SurveyorDTO;
import com.claimManagement.insuranceCompany.entities.Surveyor;
import com.claimManagement.insuranceCompany.exceptions.CustomException;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface SurveyorDAO {


    String addSurveyor(SurveyorDTO surveyorDTO);
    List<SurveyorDTO> listOfSurveyors();
    SurveyorDTO getSurveyorByEstimateLimit(int estimatelimit) throws CustomException;
    SurveyorDTO getSurveyorById(int id);
    String addSurveyorByHardCode(); 
}
