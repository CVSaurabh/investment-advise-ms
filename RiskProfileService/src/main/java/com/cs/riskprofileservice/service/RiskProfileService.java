package com.cs.riskprofileservice.service;

import com.cs.riskprofileservice.payload.FinancialData;
import com.cs.riskprofileservice.payload.RiskProfile;
import org.springframework.stereotype.Service;

@Service
public interface RiskProfileService {

//    String calculateRiskProfile(FinancialData financialData, double investmentAmount);

    RiskProfile calculateRiskProfile(FinancialData financialData, double investmentAmount);
}
