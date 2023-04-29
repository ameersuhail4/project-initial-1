package com.claimManagement.insuranceCompany.daoImp;

import com.claimManagement.insuranceCompany.DAO.SurveyorDAO;
import com.claimManagement.insuranceCompany.DTO.SurveyorDTO;
import com.claimManagement.insuranceCompany.DAO.SurveyorDAO;
import com.claimManagement.insuranceCompany.entities.Surveyor;
import com.claimManagement.insuranceCompany.exceptions.CustomException;
import com.claimManagement.insuranceCompany.repositories.SurveyorRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SurveyorDAOImp implements SurveyorDAO {

    @Autowired
    private SurveyorRepository surveyorRepository;

    @Override
    public List<SurveyorDTO> listOfSurveyors() {
        List<Surveyor> surveyors=surveyorRepository.findAll();
        List<SurveyorDTO> surveyorDTOS=surveyors.stream().map((x)->toDto(x)).collect(Collectors.toList());
        return surveyorDTOS;
    }

    @Override
    public SurveyorDTO getSurveyorByEstimateLimit(int estimatelimit) throws CustomException {
        if (estimatelimit!=0)
        {
            if(surveyorRepository.existsSurveyorByEstimateLimit(estimatelimit))
            {
                Surveyor surveyor=surveyorRepository.findByEstimateLimit(estimatelimit);
                return toDto(surveyor);
            }
            else
            {
                throw  new CustomException("No Surveyor found for estimateLoss:"+estimatelimit);
            }

        }else {
            throw  new CustomException("estimateLoss should be greater than zero");
        }

    }

    @Override
    public SurveyorDTO getSurveyorById(int id) {
        Surveyor surveyor=surveyorRepository.findSurveyorBySurveyorId(id);
        return  toDto(surveyor);
    }

    @Override
    public String addSurveyor(SurveyorDTO surveyorDTO) {
        Surveyor surveyor=toEntity(surveyorDTO);
        surveyorRepository.save(surveyor);
        return "added succesfully";
    }

	@Override
	public String addSurveyorByHardCode() {
		Surveyor s=new Surveyor(1, "Jane", "Smith", 10000);
		surveyorRepository.save(s);
		return "hardcoded data into Surveyor database";
	}
    public static SurveyorDTO toDto(Surveyor surveyor) {
        SurveyorDTO surveyorDto = new SurveyorDTO();
        surveyorDto.setSurveyorId(surveyor.getSurveyorId());
        surveyorDto.setFirstName(surveyor.getFirstName());
        surveyorDto.setLastName(surveyor.getLastName());
        surveyorDto.setEstimateLimit(surveyor.getEstimateLimit());
        return surveyorDto;
    }

    public static Surveyor toEntity(SurveyorDTO surveyorDTO) {
        Surveyor surveyor = new Surveyor();
        surveyor.setSurveyorId(surveyorDTO.getSurveyorId());
        surveyor.setFirstName(surveyorDTO.getFirstName());
        surveyor.setLastName(surveyorDTO.getLastName());
        surveyor.setEstimateLimit(surveyorDTO.getEstimateLimit());
        return surveyor;
    }
}
