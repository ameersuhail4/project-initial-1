package com.claimManagement.insuranceCompany.repositories;

import com.claimManagement.insuranceCompany.entities.Surveyor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface SurveyorRepository extends JpaRepository<Surveyor,Long>
{
    Surveyor findByEstimateLimit(int estimateLimit);

    Surveyor findSurveyorBySurveyorId(long surveyorId);
}
