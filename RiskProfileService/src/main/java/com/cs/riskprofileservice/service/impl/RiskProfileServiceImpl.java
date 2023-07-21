package com.cs.riskprofileservice.service.impl;

import com.cs.riskprofileservice.payload.FinancialData;
import com.cs.riskprofileservice.payload.RiskProfile;
import com.cs.riskprofileservice.service.RiskProfileService;
import com.cs.riskprofileservice.utility.RiskCalculator;
import org.springframework.stereotype.Service;

@Service
public class RiskProfileServiceImpl implements RiskProfileService {

    private final RiskCalculator riskCalculator;

    public RiskProfileServiceImpl(RiskCalculator riskCalculator) {
        this.riskCalculator = riskCalculator;
    }

    @Override
    public RiskProfile calculateRiskProfile(FinancialData financialData, double investmentAmount) {
        return riskCalculator.calculateRisk(financialData, investmentAmount);
    }

}
