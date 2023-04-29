package com.claimManagement.insuranceCompany.repositories;

import com.claimManagement.insuranceCompany.entities.Surveyor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface SurveyorRepository extends JpaRepository<Surveyor,Long>
{
    List<Surveyor> findAllByEstimateLimit (Integer estimateLimit);
    Surveyor findByEstimateLimit(int estimatelimit);
    Boolean existsSurveyorByEstimateLimit(int estimateLimit);
    Surveyor findSurveyorBySurveyorId(int id);
}
