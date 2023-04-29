package com.claimManagement.insuranceCompany.controllers;

import com.claimManagement.insuranceCompany.DTO.SurveyorDTO;
import com.claimManagement.insuranceCompany.daoImp.SurveyorDAOImp;
import com.claimManagement.insuranceCompany.entities.Surveyor;
import com.claimManagement.insuranceCompany.exceptions.CustomException;
import com.claimManagement.insuranceCompany.repositories.SurveyorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
public class SurveyorController {

    @Autowired
    SurveyorDAOImp surveyorDAOImp ;

    @GetMapping("/api/surveyors/{estimatedLimit}")
    SurveyorDTO getAllSurveyorsByEstimatedLimit(@PathVariable int estimatedLimit) throws CustomException
    {
        return surveyorDAOImp. getSurveyorByEstimateLimit(estimatedLimit);
     }
    @GetMapping("/api/surveyors")
    List<SurveyorDTO> getAllSurveyors()
    {
        return surveyorDAOImp.listOfSurveyors();
    }
    @GetMapping("/api/surveyors/{id}")
    SurveyorDTO getAllSurveyors(@PathVariable int id)
    {
        return surveyorDAOImp.getSurveyorById(id);
    }

    @PostMapping("/api/surveyors/new")
    String addSurveyor(@RequestBody SurveyorDTO surveyorDTO)
    {
        surveyorDAOImp.addSurveyor(surveyorDTO);
        return " Surveyor added succesfully";
    }
}
