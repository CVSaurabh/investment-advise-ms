package com.cs.riskprofileservice.controller;


import com.cs.riskprofileservice.exception.NegativeAmountException;
import com.cs.riskprofileservice.exception.RiskCalculationException;
import com.cs.riskprofileservice.payload.FinancialData;
import com.cs.riskprofileservice.payload.RiskProfile;
import com.cs.riskprofileservice.service.RiskProfileService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@AllArgsConstructor
public class RiskProfileController {

    private final RiskProfileService riskProfileService;


    @PostMapping("/calculateRisk")
    public ResponseEntity<String> calculateRisk(@Valid @RequestBody FinancialData financialData,
                                                     @RequestParam double investmentAmount) {
        if (investmentAmount < 0) {
            throw new NegativeAmountException("Investment amount cannot be negative.");
        }

        RiskProfile riskProfile = riskProfileService.calculateRiskProfile(financialData, investmentAmount);
//        if (riskProfile.getRiskLevel() == "Blocked") {
//            throw new RiskCalculationException("Unable to calculate Risk Profile. Please check the provided data.");
//        }

        return ResponseEntity.ok(riskProfile.getRiskLevel());
    }

}
