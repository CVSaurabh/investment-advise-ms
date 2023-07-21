package com.cs.investmentAdvise.payload;

import lombok.Data;

import javax.validation.constraints.Positive;

@Data
public class FinancialData {
    @Positive(message = "Income must be greater than zero.")
    private double income;

    @Positive(message = "Savings must be greater than zero.")
    private double savings;

    @Positive(message = "Liabilities must be greater than zero.")
    private double liabilities;

    @Positive(message = "Spending must be greater than zero.")
    private double spending;


}