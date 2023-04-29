package com.claimManagement.insuranceCompany.daoImp;

import com.claimManagement.insuranceCompany.DAO.ClaimDAO;
import com.claimManagement.insuranceCompany.DTO.ClaimDetailsDTO;
import com.claimManagement.insuranceCompany.entities.ClaimDetails;
import com.claimManagement.insuranceCompany.entities.Policy;
import com.claimManagement.insuranceCompany.entities.Surveyor;
import com.claimManagement.insuranceCompany.repositories.ClaimRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClaimDAOImp implements ClaimDAO {

    @Autowired
    ClaimRepository claimRepository;

    static   String generateClaimId(ClaimDetailsDTO claimDetails)
    {

        String policyNo = claimDetails.getPolicyNo();
        String year = String.valueOf(claimDetails.getDateOfAccident().getYear());
        
        return "CL" +
                policyNo.substring(policyNo.length()-4) +
                year;
    }

    @Override
    public List<ClaimDetailsDTO> getClaims() {
        List<ClaimDetails> claimDetails=claimRepository.findAll();
        List<ClaimDetailsDTO> claimDetailsDTOS=claimDetails.stream().map((x)-> toDTO(x)).collect(Collectors.toList());

        return claimDetailsDTOS;
    }

    @Override
    public String AddNewClaim(ClaimDetailsDTO claimDetailsDTO) {
        claimDetailsDTO.setClaimStatus("opened");

        String claimId=generateClaimId(claimDetailsDTO);
        if(claimRepository.existsClaimDetailsByClaimId(claimId))
        {
            return "already exist claim with status: "+claimDetailsDTO.getClaimStatus();
        }
        if(claimDetailsDTO.getEstimatedLoss()>=5000 && claimDetailsDTO.getEstimatedLoss()<10000)
        {
            claimDetailsDTO.setSurveyorfees(1000);
        }
        else if(claimDetailsDTO.getEstimatedLoss()>=10000 && claimDetailsDTO.getEstimatedLoss()<20000)
        {
            claimDetailsDTO.setSurveyorfees(2000);
        }
        else if(claimDetailsDTO.getEstimatedLoss()>=20000 && claimDetailsDTO.getEstimatedLoss()<70000)
        {
            claimDetailsDTO.setSurveyorfees(7000);
        }
        ClaimDetails claimDetails=toEntity(claimDetailsDTO);
        claimDetails.setClaimId(claimId);
        claimDetails.setClaimStatus("open");
        ClaimDetails claimDetails1=claimRepository.save(claimDetails);
        return "added: "+claimDetails1.getClaimId();
    }


    @Override
    public String UpdateByClaimID(String claimId,ClaimDetailsDTO claimDetailsDTO) {
        claimRepository.findByClaimId(claimId);
        return "updated";
    }

    @Override
    public ClaimDetailsDTO getClaimById(String id) {
        ClaimDetails claimDetails=claimRepository.findByClaimId(id);
        return  toDTO(claimDetails);
    }
    public ClaimDetailsDTO toDTO(ClaimDetails claimDetails) {
        ClaimDetailsDTO claimDetailsDto = new ClaimDetailsDTO();

        claimDetailsDto.setClaimId(claimDetails.getClaimId());
        claimDetailsDto.setPolicyNo(claimDetails.getPolicy().getPolicyNo());
        claimDetailsDto.setEstimatedLoss(claimDetails.getEstimatedLoss());
        claimDetailsDto.setDateOfAccident(claimDetails.getDateOfAccident());
        claimDetailsDto.setClaimStatus(claimDetails.getClaimStatus());
        claimDetailsDto.setSurveyorId(claimDetails.getSurveyor().getSurveyorId());
        claimDetailsDto.setAmtApprovedBySurveyor(claimDetails.getAmtApprovedBySurveyor());
        claimDetailsDto.setInsuranceCompanyApproval(claimDetails.isInsuranceCompanyApproval());
        claimDetailsDto.setWithdrawClaim(claimDetails.isWithdrawClaim());
        claimDetailsDto.setSurveyorfees(claimDetails.getSurveyorfees());

        return claimDetailsDto;
    }
    public ClaimDetails toEntity(ClaimDetailsDTO claimDetailsDto) {
        ClaimDetails claimDetails = new ClaimDetails();

        claimDetails.setClaimId(claimDetailsDto.getClaimId());
        claimDetails.setEstimatedLoss(claimDetailsDto.getEstimatedLoss());
        claimDetails.setDateOfAccident(claimDetailsDto.getDateOfAccident());
        claimDetails.setClaimStatus(claimDetailsDto.getClaimStatus());
        claimDetails.setAmtApprovedBySurveyor(claimDetailsDto.getAmtApprovedBySurveyor());
        claimDetails.setInsuranceCompanyApproval(claimDetailsDto.isInsuranceCompanyApproval());
        claimDetails.setWithdrawClaim(claimDetailsDto.isWithdrawClaim());
        claimDetails.setSurveyorfees(claimDetailsDto.getSurveyorfees());

        Policy policy = new Policy();
        policy.setPolicyNo(claimDetailsDto.getPolicyNo());
        claimDetails.setPolicy(policy);

        Surveyor surveyor = new Surveyor();
        surveyor.setSurveyorId(claimDetailsDto.getSurveyorId());
        claimDetails.setSurveyor(surveyor);

        return claimDetails;
    }

}
