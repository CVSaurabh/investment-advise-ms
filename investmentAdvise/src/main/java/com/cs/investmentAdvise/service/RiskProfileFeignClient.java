package com.cs.investmentAdvise.service;

import com.cs.investmentAdvise.payload.FinancialData;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("riskprofile")
public interface RiskProfileFeignClient {

    @RequestMapping(method = RequestMethod.POST, value = "calculateRisk", consumes = "application/json")
    String calculateRiskProfile(@RequestBody FinancialData financialData,@RequestParam("investmentAmount") double investmentAmount);
}