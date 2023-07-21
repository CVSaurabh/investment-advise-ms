package com.cs.investmentAdvise.controller;

import com.cs.investmentAdvise.exception.NegativeAmountException;
import com.cs.investmentAdvise.exception.RiskCalculationException;
import com.cs.investmentAdvise.payload.FinancialData;
import com.cs.investmentAdvise.service.RiskProfileFeignClient;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import javax.validation.Valid;

@RestController
public class InvestmentAdviseController {

    private final WebClient.Builder webClientBuilder;

    private final RiskProfileFeignClient riskProfileFeignClient;

//    @Autowired
//    RiskProfileFeignClient riskProfileFeignClient;

    public InvestmentAdviseController(WebClient.Builder webClientBuilder, RiskProfileFeignClient riskProfileFeignClient) {
        this.webClientBuilder = webClientBuilder;
        this.riskProfileFeignClient = riskProfileFeignClient;
    }

    @PreAuthorize("hasAuthority('SCOPE_offline_access')")
    @GetMapping("/getInvestmentAdvice")
//    @RateLimiter(name = "investmentAdviseRateLimiter", fallbackMethod = "rateLimiterFallback")
   public ResponseEntity<String> getInvestmentAdvice(@Valid  @RequestBody FinancialData financialData,
                                                      @RequestParam double investmentAmount) {

        if (investmentAmount < 0) {
            throw new NegativeAmountException("Investment amount cannot be negative.");
        }
        String riskAdvice;

        String riskRating = riskProfileFeignClient.calculateRiskProfile(financialData, investmentAmount);

        if (riskRating.equals("Low Risk")) {
            riskAdvice = "Your Investment are in Low Risk zone.";
        } else if (riskRating.equals("Medium Risk")) {
            riskAdvice = "Your Investment are in Medium Risk zone.";
        }else if (riskRating.equals("High Risk")) {
            riskAdvice = "Your Investment are in High Risk zone.Please consider changing your investment strategy.";
        } else {
            throw new RiskCalculationException("Unable to calculate Risk Profile. Please check the investment amount.");
        }

        String response = "Risk Advice: " + riskAdvice;

        return ResponseEntity.ok(response);
    }



    public ResponseEntity<String> rateLimiterFallback(Throwable throwable) {
        String msg =  "Rate Limit Exceeded: The number of requests to the riskProfileService is currently limited. Please wait and try again later.";

        return new ResponseEntity<>(msg, HttpStatus.BAD_REQUEST);

    }


    public  ResponseEntity<String> circuitBreakerFallback() {
        String unavailableMsg = "Service Unavailable: The riskProfileService is currently experiencing issues. Please try again later.";

        return new ResponseEntity<>(unavailableMsg, HttpStatus.BAD_REQUEST);
    }

}