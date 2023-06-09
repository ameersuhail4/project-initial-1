package com.claimManagement.insuranceCompany.serviceImp;

import com.claimManagement.insuranceCompany.service.ClaimService;
import com.claimManagement.insuranceCompany.DTO.ClaimDetailsDTO;
import com.claimManagement.insuranceCompany.DTO.PolicyDTO;
import com.claimManagement.insuranceCompany.DTO.SurveyorDTO;
import com.claimManagement.insuranceCompany.entities.ClaimDetails;
import com.claimManagement.insuranceCompany.entities.Policy;
import com.claimManagement.insuranceCompany.entities.Surveyor;
import com.claimManagement.insuranceCompany.exceptions.CustomException;
import com.claimManagement.insuranceCompany.repositories.ClaimRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Constants;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ClaimServiceImp implements ClaimService {

    @Autowired
    ClaimRepository claimRepository;

    @Autowired
    PolicyServiceImp policyServiceImp;

    @Autowired
    SurveyorServiceImp surveyorServiceImp;

    // Generate claim ID based on the policy number and the year of the accident.
    static   String generateClaimId(ClaimDetailsDTO claimDetails)
    {

        String policyNo = claimDetails.getPolicyNo();
        String year = String.valueOf(claimDetails.getDateOfAccident().getYear());
        
        return "CL" +
                policyNo.substring(policyNo.length()-4) +
                year;
    }

    // Retrieve all claims
    @Override
    public List<ClaimDetailsDTO> getClaims() throws CustomException {
        List<ClaimDetails> claimDetails=claimRepository.findAll();
        //Throws Exception when no claims exists.
        if (claimDetails.isEmpty())
        {
            throw new CustomException("No claims Exist");
        }
        return claimDetails.stream().map((x)-> toDTO(x)).collect(Collectors.toList());
    }

    // Add a new claim.
    @Override
    public ClaimDetails AddNewClaim(ClaimDetailsDTO claimDetailsDTO) throws CustomException {

        //System.out.println("getting request data:"+claimDetailsDTO);
        claimDetailsDTO.setClaimStatus("opened");
        String claimId=generateClaimId(claimDetailsDTO);

        // If a claim with the same ID already exists, throw an exception.
        if(claimRepository.existsClaimDetailsByClaimId(claimId))
        {
            throw new CustomException( "already exist claim with status: "+claimDetailsDTO.getClaimStatus());
        }

        // Determine the surveyor fee based on the estimated loss.
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
        PolicyDTO policyDTO;
        SurveyorDTO surveyorDTO;

        // Retrieve the policy associated with the claim.
        policyDTO= policyServiceImp.getById(claimDetailsDTO.getPolicyNo());
        int estimateLimit=claimDetailsDTO.getEstimatedLoss();

        // Retrieve the surveyor with the appropriate estimate limit.
        surveyorDTO= surveyorServiceImp.getSurveyorByEstimateLimit(estimateLimit);
        Policy policy = PolicyServiceImp.toEntity(policyDTO);
        Surveyor surveyor= SurveyorServiceImp.toEntity(surveyorDTO);


        claimDetailsDTO.setSurveyorId(surveyorDTO.getSurveyorId());

        ClaimDetails claimDetails=toEntity(claimDetailsDTO);
        claimDetails.setClaimId(claimId);
        claimDetails.setClaimStatus("open");
        claimDetails.setPolicy(policy);
        claimDetails.setSurveyor(surveyor);
        claimDetails.setAmtApprovedBySurveyor(claimDetailsDTO.getEstimatedLoss()-claimDetailsDTO.getSurveyorfees());
        //System.out.println("or:"+claimDetails);
        ClaimDetails claimDetails1=claimRepository.save(claimDetails);;
       if (claimDetails1==null)
       {
           throw new RuntimeException();
       }
        return  claimDetails1;

    }

    //update claim by setting claim status.
    @Override
    public ClaimDetails UpdateByClaimID(String claimId,ClaimDetailsDTO claimDetailsDTO) throws CustomException {
        Optional<ClaimDetails> claimDetailsOptional=claimRepository.findById(claimId);
        if (!claimDetailsOptional.isPresent())
        {
            throw new CustomException("No Claim exist with claimId: "+claimId);
        } else if (claimDetailsOptional.get().getClaimStatus()=="closed") {
            throw new CustomException("Claim is closed . Unable to update");
        }
        ClaimDetails claimDetails= claimDetailsOptional.get();
        claimDetails.setClaimStatus(claimDetailsDTO.getClaimStatus());
        return claimRepository.save(claimDetails);
    }

    //Entity to DTO convertion
    @Override
    public ClaimDetailsDTO getClaimById(String id) throws CustomException {
        Optional<ClaimDetails> claimDetails= Optional.of(claimRepository.findById(id).orElseThrow(() -> new CustomException("No Claim found with id: "+id)));
        return  toDTO(claimDetails.get());
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

    //DTO to Entity Conversion
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
