package com.cs.riskprofileservice.utility;

import com.cs.riskprofileservice.payload.FinancialData;
import com.cs.riskprofileservice.payload.RiskProfile;
import org.springframework.stereotype.Component;

@Component
public class RiskCalculator {
    public static final String LOW_RISK = "Low Risk";
    public static final String MEDIUM_RISK = "Medium Risk";
    public static final String HIGH_RISK = "High Risk";
    public static final String BLOCK_REQUEST = "Blocked";

    public RiskProfile calculateRisk(FinancialData financialData, double investmentAmount) {
        double availableCash = financialData.getIncome() + financialData.getSavings() - financialData.getLiabilities() - financialData.getSpending();

        if (investmentAmount < availableCash * 0.25) {
            return new RiskProfile(LOW_RISK);
        } else if (investmentAmount >= availableCash * 0.25 && investmentAmount < availableCash * 0.75) {
            return new RiskProfile(MEDIUM_RISK);
        } else if (investmentAmount >= availableCash * 0.75 && investmentAmount != availableCash) {
            return new RiskProfile(HIGH_RISK);
        } else  {
            return new RiskProfile(BLOCK_REQUEST);
        }

    }
}