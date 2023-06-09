package com.claimManagement.insuranceCompany.controllers;

import com.claimManagement.insuranceCompany.DTO.SurveyorDTO;
import com.claimManagement.insuranceCompany.exceptions.CustomException;
import com.claimManagement.insuranceCompany.serviceImp.SurveyorServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "http://localhost:4200")

@RestController
public class SurveyorController {

    @Autowired
    SurveyorServiceImp surveyorServiceImp;

    @GetMapping("/api/surveyors/estimatedLimit/{estimatedLimit}")
    SurveyorDTO getAllSurveyorsByEstimatedLimit(@PathVariable int estimatedLimit) throws CustomException {
        return surveyorServiceImp. getSurveyorByEstimateLimit(estimatedLimit);
     }
    @GetMapping("/api/surveyors")
    List<SurveyorDTO> getAllSurveyors() throws CustomException {
        return surveyorServiceImp.listOfSurveyors();
    }
    @GetMapping("/api/surveyors/id/{id}")
    SurveyorDTO getAllSurveyors(@PathVariable int id) throws CustomException {
        return surveyorServiceImp.getSurveyorById(id);
    }

    @PostMapping("/api/surveyors/new")
    String addSurveyor(@RequestBody SurveyorDTO surveyorDTO)
    {
        surveyorServiceImp.addSurveyor(surveyorDTO);
        return " Surveyor added succesfully";
    }
}
