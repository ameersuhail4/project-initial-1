package com.claimManagement.insuranceCompany.serviceImp;

import com.claimManagement.insuranceCompany.exceptions.CustomException;
import com.claimManagement.insuranceCompany.service.SurveyorService;
import com.claimManagement.insuranceCompany.DTO.SurveyorDTO;
import com.claimManagement.insuranceCompany.entities.Surveyor;
import com.claimManagement.insuranceCompany.repositories.SurveyorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SurveyorServiceImp implements SurveyorService {

    //Injecting repository.
    @Autowired
    private SurveyorRepository surveyorRepository;

    //Retrieve list of Surveyors.
    @Override
    public List<SurveyorDTO> listOfSurveyors() throws CustomException {
        List<Surveyor> surveyors=surveyorRepository.findAll();
        if (surveyors.isEmpty())
        {
            throw new CustomException("No surveyors exist!");
        }
        List<SurveyorDTO> surveyorDTOS=surveyors.stream().map((x)->toDto(x)).collect(Collectors.toList());
        return surveyorDTOS;
    }

    //Retrieve Surveyor by EstimateLimit
    @Override
    public SurveyorDTO getSurveyorByEstimateLimit(int estimatelimit) throws CustomException {
        Surveyor surveyor=surveyorRepository.findByEstimateLimit(estimatelimit);
        if (surveyor==null)
        {
            throw new CustomException("No Surveyor Exists with estimated Limit: "+estimatelimit);
        }
        return toDto(surveyor);

    }

    //Retrieve surveyor by id.
    @Override
    public SurveyorDTO getSurveyorById(int id) throws CustomException {
        Surveyor surveyor=surveyorRepository.findSurveyorBySurveyorId(id);
        if (surveyor==null)
        {
            throw new CustomException("No Surveyor Exists with id: "+id);
        }
        return  toDto(surveyor);
    }

    //adding a new Surveyor.
    @Override
    public Surveyor addSurveyor(SurveyorDTO surveyorDTO) {
        Surveyor surveyor=toEntity(surveyorDTO);
        return surveyorRepository.save(surveyor);
    }

	@Override
	public String addSurveyorByHardCode() {
		Surveyor s=new Surveyor(1, "Jane", "Smith", 10000);
		surveyorRepository.save(s);
		return "hardcoded data into Surveyor database";
	}

    //Entity to DTO
    public static SurveyorDTO toDto(Surveyor surveyor) {
        SurveyorDTO surveyorDto = new SurveyorDTO();
        surveyorDto.setSurveyorId(surveyor.getSurveyorId());
        surveyorDto.setFirstName(surveyor.getFirstName());
        surveyorDto.setLastName(surveyor.getLastName());
        surveyorDto.setEstimateLimit(surveyor.getEstimateLimit());
        return surveyorDto;
    }

    //DTO to Entity
    public static Surveyor toEntity(SurveyorDTO surveyorDTO) {
        Surveyor surveyor = new Surveyor();
        surveyor.setSurveyorId(surveyorDTO.getSurveyorId());
        surveyor.setFirstName(surveyorDTO.getFirstName());
        surveyor.setLastName(surveyorDTO.getLastName());
        surveyor.setEstimateLimit(surveyorDTO.getEstimateLimit());
        return surveyor;
    }
}
