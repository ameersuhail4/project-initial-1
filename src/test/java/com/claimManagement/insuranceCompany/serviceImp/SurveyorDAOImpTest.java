package com.claimManagement.insuranceCompany.serviceImp;

import com.claimManagement.insuranceCompany.DTO.SurveyorDTO;
import com.claimManagement.insuranceCompany.entities.Surveyor;
import com.claimManagement.insuranceCompany.exceptions.CustomException;
import com.claimManagement.insuranceCompany.repositories.SurveyorRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SurveyorDAOImpTest {

    @Mock
    SurveyorRepository  surveyorRepository;
    @InjectMocks
    SurveyorServiceImp surveyorServiceImp;
    List<Surveyor> surveyors=new ArrayList<>();
    Surveyor  surveyor;
    @BeforeEach
    void setUp()
    {
        surveyor=new Surveyor(1,"Ameer","Shaik",10000);
    }
    @Test
    void listOfSurveyors() throws CustomException {
        surveyors.add(surveyor);
        when(surveyorRepository.findAll()).thenReturn(surveyors);
        List<SurveyorDTO> surveyorList= surveyorServiceImp.listOfSurveyors();
        assertNotNull(surveyorList);
    }

    @Test
    void getSurveyorByEstimateLimit() throws CustomException {
        //when(surveyorRepository.existsSurveyorByEstimateLimit(10000)).thenReturn(true);
        when(surveyorRepository.findByEstimateLimit(10000)).thenReturn(surveyor);
        SurveyorDTO surveyorDTO= surveyorServiceImp.getSurveyorByEstimateLimit(10000);
        assertNotNull(surveyorDTO);
    }

    @Test
    void getSurveyorById() throws CustomException {
        when(surveyorRepository.findSurveyorBySurveyorId(1)).thenReturn(surveyor);
        SurveyorDTO surveyorDTO= surveyorServiceImp.getSurveyorById(1);
        assertNotNull(surveyorDTO);
    }

    @Test
    void addSurveyor() {
        SurveyorDTO surveyorDTO= SurveyorServiceImp.toDto(surveyor);
        when(surveyorRepository.save(surveyor)).thenReturn(surveyor);
        Surveyor surveyor1= surveyorServiceImp.addSurveyor(surveyorDTO);
        assertNotNull(surveyor1);
    }
}